/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package api.security.toolsign;

import java.io.*;

/**
 * === Steps for the Code Signer:
 * java api.security.toolsign.Count api/security/toolsign/data: 
 * 
 * jar cvf Count.jar api/security/toolsign/Count.class  生成jar;
 * 
 * 生成self-signed 证书(开发测试用): keytool -genkey -alias signFiles -keystore examplestore    
    examplestore password:  aaaaaa   
    Susan Jones
    Purchasing
    ExampleCompany
    Cupertino
    CA
    US
    signFiles password: bbbbbb
  CA认证的证书:   
       生成CSR: keytool -certreq -alias alias -file csrFile : 将csrFile提交给CA机构(例如VeriSign), 返回CA认证的证书;
       导入CA的返回: keytool -import -trustcacerts -keystore storefile -alias alias -file certReplyFile 
 * 
 * 进行签名: jarsigner -keystore c:\\MyInstall\\Java\\Oracle_JDK\\jdk1.7.0_21\\bin\\examplestore -signedjar sCount.jar Count.jar signFiles
 * 导出证书: keytool -export -keystore c:\\MyInstall\\Java\\Oracle_JDK\\jdk1.7.0_21\\bin\\examplestore -alias signFiles -file Example.cer
 * 
 * 
 * === Steps for the Code Receiver
 * java -Djava.security.manager  -cp c:\\IBM\\my\\java\\MyJava\\bin\\sCount.jar api.security.toolsign.Count c:\\IBM\\my\\java\\MyJava\\bin\\api\\security\\data
 *    必须要有security.manager， 否则不会进行权限检查, 执行就正常了;  不会报:
 *    Exception in thread "main" java.security.AccessControlException: access denied (
        "java.io.FilePermission" "c:\IBM\my\java\MyJava\bin\api\security\data" "read")
        at java.security.AccessControlContext.checkPermission(Unknown Source)
        at java.security.AccessController.checkPermission(Unknown Source)
 * 
 * keytool -import -alias susan -file Example.cer -keystore c:\\MyInstall\\Java\\Oracle_JDK\\jdk1.7.0_21\\bin\\exampleraystore
 *    导入证书,会生成exampleraystore; 需要询问Susan fingerprints: keytool -printcert -file Example.cer
 *    产生的结果与上面的keytool -import进行比对, 如果相同, 则包含public key 的证书没有被修改过;
 *
 * 编辑policy: policytool.exe; keystore的url中输入: file:c:\\MyInstall\\Java\\Oracle_JDK\\jdk1.7.0_21\\bin\\exampleraystore
 *            add permission: filepermission;
 *            保存为 exampleraypolicy
 * 
 * java -Djava.security.manager -Djava.security.policy=api/security/exampleraypolicy -cp c:\\IBM\\my\\java\\MyJava\\bin\\sCount.jar api.security.toolsign.Count c:\\IBM\\my\\java\\MyJava\\bin\\api\\security\\data
 *    执行就可以访问了;
 * 
 */
public class Count {
    public static void countChars(InputStream in) throws IOException {
        int count = 0;

        while (in.read() != -1)
            count++;

        System.out.println("Counted " + count + " chars.");
    }

    public static void main(String[] args) throws Exception {
        if (args.length >= 1)
            countChars(new FileInputStream(args[0]));
        else
            System.err.println("Usage: Count filename");
    }
}
