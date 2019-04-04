package com.huawei.openstack4j.openstack.identity.signer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SimpleTimeZone;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.huawei.openstack4j.core.transport.HttpRequest;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;
import com.huawei.openstack4j.utils.BinaryUtils;

/**
 *
 * @author QianBiao.NG
 * @date   2018-03-13 16:55:38
 */
public class AKSK {

	public static class Constants {
		public static final String LINE_SEPARATOR = "\n";
		public static final String SDK_NAME = "SDK";
		public static final String SDK_TERMINATOR = "sdk_request";
		public static final String SDK_SIGNING_ALGORITHM = "SDK-HMAC-SHA256";
		public static final String X_SDK_DATE = "X-Sdk-Date";
		public static final String X_SDK_CONTENT_SHA256 = "x-sdk-content-sha256";
		public static final String AUTHORIZATION = "Authorization";
		public static final String HOST = "Host";
		public static final String CONTENT_TYPE = "Content-Type";
		public static final String CONTENT_LENGTH = "Content-Length";
		// sorted to be signed headers
		public static final List<String> TO_SIGNED_HEADERS = Lists.newArrayList(HOST, X_SDK_DATE);
	}

	@Getter
	@Setter
	@Builder
	public static class Credential {
		String ak;
		String sk;
	}

	// /** SHA256 hash of an empty request body **/
	public static final String EMPTY_BODY_SHA256 = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

	/** format strings for the date/time and date stamps required during signing **/
	public static final String ISO8601BasicFormat = "yyyyMMdd'T'HHmmss'Z'";
	public static final String DateStringFormat = "yyyyMMdd";

	protected static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(ISO8601BasicFormat);
	protected static final SimpleDateFormat dateStampFormat = new SimpleDateFormat(DateStringFormat);
	static {
		System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
	}

	public static HashMap<String, String> sign(HttpRequest<?> request, Credential credential) {
		
		// Date now = dateTimeFormat.parse("20180314T080059Z"); // 设置基准时间
		Date now = new Date(); // 设置基准时间
		HashMap<String, String> authenticationHeaders = new HashMap<>();

		// Step 1: add basic headers required by V4

		// Step 1.1: Add Host header
		String endpoint = request.getEndpoint();
		String requestPath = request.getPath();
		URL url = convertToURL(endpoint, requestPath);
		String host = buildCanonicalHost(url);
		authenticationHeaders.put(Constants.HOST, host);

		// Step 1.2: add X-Sdk-Date
		dateTimeFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
		dateStampFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
		String dateTimeStamp = dateTimeFormat.format(now);
		String datestamp = dateStampFormat.format(now);
		authenticationHeaders.put(Constants.X_SDK_DATE, dateTimeStamp);

		// Step 2: Create canonical URI--the part of the URI from domain to query
		String pathOld = url.getPath();
		String path  = pathOld.replaceAll(":", "%3A");
		String canonicalURI = path.endsWith("/") ? path : path + "/";

		// Step 3: Create the canonical query string. In this example (a GET request),
		// request parameters are in the query string. Query string values must
		// be URL-encoded (space=%20). The parameters must be sorted by name.
		// For this example, the query string is pre-formatted in the request_parameters variable.
		String query = url.getQuery();
		Map<String, List<Object>> parameters = request.getQueryParams();
		String canonicalQueryString = buildCanonicalQueryString(query, parameters);

		// Step 4: Create the list of signed headers. This lists the headers
		// in the canonical_headers list, delimited with ";" and in alpha order.
		// Note: The request can include any headers; canonical_headers and
		// signed_headers lists those that you want to be included in the
		// hash of the request. "Host" and "x-sdk-date" are always required.
		// In V4 signer, we only use required header - host & x-sdk-date.
		String signedHeaderNames = Joiner.on(";").join(Constants.TO_SIGNED_HEADERS).toLowerCase();

		// Step 5: Create the canonical headers and signed headers. Header names
		// and value must be trimmed and lower-case, and sorted in ASCII order.
		// Note that there is a trailing \n.
		String canonicalHeaders = buildCanonicalHeaders(authenticationHeaders);

		// Step 6: Create payload hash (hash of the request body content). For GET
		// requests, the payload is an empty string ("").
		String payloadHash = buildPayloadHash(request);

		// Step 7: Combine elements to create canonical request
		String canonicalRequest = buildCanonicalRequest(request.getMethod().name(), canonicalURI, canonicalQueryString,
				canonicalHeaders, signedHeaderNames, payloadHash);
		String canonicalRequestHash = BinaryUtils.toHex(sha256(canonicalRequest));
		// ************* TASK 2: CREATE THE STRING TO SIGN*************
		// Match the algorithm to the hashing algorithm you use, either SHA-1 or SHA-256 (recommended)
		String[] scopeArray = new String[] { datestamp, request.getRegion(), request.getService().getServiceName(),
				Constants.SDK_TERMINATOR };
		String credentialScope = Joiner.on("/").join(scopeArray);
		String stringToSign = getStringToSign(Constants.SDK_SIGNING_ALGORITHM, dateTimeStamp, credentialScope,
				canonicalRequestHash);

		// ************* TASK 3: CALCULATE THE SIGNATURE *************
		// Create the signing key using the function defined above.
		String signatureString = signature(request.getService().getServiceName(), datestamp, request.getRegion(),
				stringToSign, credential.getSk());

		// ************* TASK 4: ADD SIGNING INFORMATION TO THE REQUEST *************
		// The signing information can be either in a query string value or in
		// a header named Authorization. This code shows how to use a header.
		// Create authorization header and add to request headers
		StringBuilder authorization = new StringBuilder(Constants.SDK_SIGNING_ALGORITHM).append(" ");
		authorization.append("Credential=").append(credential.getAk()).append("/").append(credentialScope).append(", ");
		authorization.append("SignedHeaders=").append(signedHeaderNames).append(", ");
		authorization.append("Signature=").append(signatureString);
		authenticationHeaders.put(Constants.AUTHORIZATION, authorization.toString());

		return authenticationHeaders;

	}

	/**
	 * @param request
	 * @return
	 */
	public static String buildPayloadHash(HttpRequest<?> request) {
		if (!Strings.isNullOrEmpty(request.getJson())) {
			return BinaryUtils.toHex(sha256(request.getJson()));
		}
		
		try {
			if (request.getEntity() != null) {
				Object entity = request.getEntity();
				String payload = ObjectMapperSingleton.getContext(entity.getClass()).writeValueAsString(entity);
				return BinaryUtils.toHex(sha256(payload));
			}
		} catch (JsonProcessingException e) {
			// should not happen
			throw new RuntimeException("Could not serialize entity", e);
		}
		
		return EMPTY_BODY_SHA256;
	}
	

	/**
	 * @param endpoint
	 * @param requestPath
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL convertToURL(String endpoint, String requestPath) {
		try {
			return new URL(endpoint + requestPath);
		} catch (MalformedURLException e) {
			throw new RuntimeException("Endpoint illegal", e);
		}
	}

	/**
	 * @param request
	 * @param datestamp
	 * @param session
	 * @param stringToSign
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String signature(String serviceName, String datestamp, String region, String stringToSign,
			String secretKey) {
		try {
			byte[] keySecret = (Constants.SDK_NAME + secretKey).getBytes("UTF-8");
			byte[] kDate = hmac(keySecret, datestamp);
			byte[] kRegion = hmac(kDate, region);
			byte[] kService = hmac(kRegion, serviceName);
			byte[] kSigning = hmac(kService, Constants.SDK_TERMINATOR);
			byte[] signature = hmac(kSigning, stringToSign);
			return BinaryUtils.toHex(signature);
		} catch (UnsupportedEncodingException e) {
			// should not happen
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param sdkSigningAlgorithm
	 * @param dateTimeStamp
	 * @param credentialScope
	 * @param canonicalRequestHash
	 * @return 
	 */
	private static String getStringToSign(String... segments) {
		return Joiner.on(Constants.LINE_SEPARATOR).join(segments);
	}

	/**
	 * @param method
	 * @param canonicalURI
	 * @param canonicalQueryString
	 * @param canonicalHeaders
	 * @param signedHeaderNames
	 * @param payloadHash
	 * @return
	 */
	private static String buildCanonicalRequest(String... segments) {
		return Joiner.on(Constants.LINE_SEPARATOR).join(segments);
	}

	/**
	 * Create the canonical headers and signed headers. Header names 
	 * and value must be trimmed and lowercase, and sorted in ASCII order.
	 * Note that there is a trailing \n.
	 * 
	 * @param authenticationHeaders
	 * @return
	 */
	private static String buildCanonicalHeaders(HashMap<String, String> authenticationHeaders) {
		StringBuilder sb = new StringBuilder();
		for (String sortedKey : Constants.TO_SIGNED_HEADERS) {
			sb.append(sortedKey.toLowerCase()).append(":").append(authenticationHeaders.get(sortedKey));
			sb.append(Constants.LINE_SEPARATOR);
		}
		return sb.toString();
	}

	/**
	 * @param url
	 * @return 
	 */
	public static String buildCanonicalHost(URL url) {
		String host = url.getHost();
		int port = url.getPort();
		if (port > -1) {
			host.concat(":" + Integer.toString(port));
		}
		return host;
	}

	public static String buildCanonicalQueryString(String query, Map<String, List<Object>> parameters) {

		SortedMap<String, String> sorted = new TreeMap<String, String>();

		// get parameters from path query string
		if (query != null && !query.isEmpty()) {
			String[] splitted = query.split("&");
			for (String split : splitted) {
				String[] kv = split.split("=");
				if (kv.length == 2) {
					sorted.put(urlEncode(kv[0]), urlEncode(kv[1]));
				}
			}
		}

		if (parameters != null && !parameters.isEmpty()) {
			Iterator<Map.Entry<String, List<Object>>> iterator = parameters.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, List<Object>> pair = iterator.next();
				String key = pair.getKey();
				List<Object> values = pair.getValue();
				for (Object value : values) {
					String valueString = value.toString();
					if("tags".equals(key)||"metadata".equals(key)){
						if(valueString.contains("%7B")||valueString.contains("%7D")||valueString.contains("%7b")||valueString.contains("%7d")){
							valueString = valueString.replace("%7B","{").replace("%7b","{")
									.replace("%7D","}").replace("%7d","}");
						}
					}
					sorted.put(urlEncode(key), urlEncode(valueString));
				}
			}
		}

		StringBuilder builder = new StringBuilder();
		Iterator<Entry<String, String>> itr = sorted.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<String, String> pair = itr.next();
			builder.append(pair.getKey());
			builder.append("=");
			builder.append(pair.getValue());
			if (itr.hasNext()) {
				builder.append("&");
			}
		}

		return builder.toString();
	}

	public static String urlEncode(String url) {
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UTF-8 encoding is not supported.", e);
		}
	}

	/**
	* Hashes the string contents (assumed to be UTF-8) using the SHA-256
	* algorithm.
	*/
	public static byte[] sha256(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(text.getBytes("UTF-8"));
			return md.digest();
		} catch (Exception e) {
			throw new RuntimeException("Unable to compute hash while signing request: " + e.getMessage(), e);
		}
	}

	/**
	 * Hashes the byte array using the SHA-256 algorithm.
	 */
	public static byte[] sha256(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(data);
			return md.digest();
		} catch (Exception e) {
			throw new RuntimeException("Unable to compute hash while signing request: " + e.getMessage(), e);
		}
	}

	protected static byte[] hmac(byte[] key, String data) {
		try {
			Mac hmac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");
			hmac.init(secretKeySpec);
			return hmac.doFinal(data.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException("Unable to calculate a request signature: " + e.getMessage(), e);
		}
	}

	protected static byte[] sign(String stringData, byte[] key, String algorithm) {
		try {
			byte[] data = stringData.getBytes("UTF-8");
			Mac mac = Mac.getInstance(algorithm);
			mac.init(new SecretKeySpec(key, algorithm));
			return mac.doFinal(data);
		} catch (Exception e) {
			throw new RuntimeException("Unable to calculate a request signature: " + e.getMessage(), e);
		}
	}
		
	public static void main(String[] args) {
		System.out.println(BinaryUtils.toHex(sha256("")));
	}
}
