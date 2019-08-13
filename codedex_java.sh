 #!/bin/sh
        export JAVA_HOME=/root/buildbox/jdk1.8.0_151
        export MAVN_HOME=/root/buildbox/apache-maven-3.3.3
 
        export CODEDEX_PATH=/root/buildbox/CodeDEX_V3
        export FORTIFY_HOME=$CODEDEX_PATH/tool/tools/fortify_17.20
        export COVERITY_HOME=$CODEDEX_PATH/tool/tools/coverity_2018.01
        export ZA_HOME=$CODEDEX_PATH/tool/7za/Linux
        export COMPILER_EXTRACT=$CODEDEX_PATH/compiler-extract
        export CODEMARS_HOME=$CODEDEX_PATH/tool/tools/codemars_Newest
 
        #path
        export PATH=$JAVA_HOME/bin:$MAVN_HOME/bin:$FORTIFY_HOME/bin:$COVERITY_HOME/bin:$COMPILER_EXTRACT/bin:$CODEMARS_HOME/bin:$PATH
 
        # FORTIFY_BUILD_ID可设置自己服务的ID值，这个ID名由数字、字母、下划线组成，不支持包含中划线-
        export FORTIFY_BUILD_ID=fuxi_codedex_test1
        export language=java
 
        #使用环境变量INTER_DIR(中间文件目录)、SRC_WS(源码路径)、SCAN_DIR(待扫描的代码相对路径)
        export inter_dir=$INTER_DIR 
        export codemars_tmp_dir=$inter_dir/codemars_tmp
        export cov_tmp_dir=$inter_dir/cov_tmp
        export for_tmp_dir=$inter_dir/for_tmp
        export project_root=$SRC_WS/$SCAN_DIR
 
        rm -rf $inter_dir
 
        cd $CODEMARS_HOME
        sh CodeMars.sh -j -source $project_root -output $codemars_tmp_dir/CodeMars.json  
 
        cd $project_root
        cov-build --dir "$cov_tmp_dir" mvn -f sdk-java-master/pom.xml clean install -Dmaven.test.skip=true
 
        cd $CODEDEX_PATH/compiler-extract/bin
        compiler-extract -fh $FORTIFY_HOME -lang $language -dir $inter_dir -b $FORTIFY_BUILD_ID
 
        cd $inter_dir
        sourceanalyzer -b $FORTIFY_BUILD_ID -Dcom.fortify.sca.ProjectRoot=$for_tmp_dir -export-build-session $FORTIFY_BUILD_ID.mbs
 
        cd $cov_tmp_dir
        $ZA_HOME/7za a -tzip coverity.zip * -r
        mv coverity.zip "$inter_dir"
 
        cd $inter_dir
        $ZA_HOME/7za a -tzip fortify.zip $FORTIFY_BUILD_ID.mbs
        mv fortify.zip "$inter_dir"  
 
        cd $codemars_tmp_dir
        zip codemars.zip CodeMars.json 
        cp codemars.zip $inter_dir/