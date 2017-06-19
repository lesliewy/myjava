package apache;


import java.io.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpGet;

public class HiInvokeServiceClient
{

//    static Logger log = HiLog.getLogger("SYS.trc");

    public HiInvokeServiceClient()
    {
    }

    public static void main(String args[])
        throws Exception
    {
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpGet httpget= new HttpGet("http://127.0.0.1:38031/sysmng/OMNGPUB1/MNG11002.dow");
    	HttpResponse response = httpclient.execute(httpget);
    	HttpEntity entity = response.getEntity();
    	if (entity != null) { 
    		InputStream instream = entity.getContent(); 
    		int l; 
    		byte[] tmp = new byte[2048]; 
    		while ((l = instream.read(tmp)) != -1) { 
    		} 
    	}
//        HttpClient httpclient = new DefaultHttpClient();
//        HttpPost httppost = new HttpPost("http://127.0.0.1:38031/sysmng/OMNGPUB1/MNG11002.dow");
//        PostMethod method = new PostMethod(args[0]);
//        Options options = new Options();
//        options.addOption("f", true, "data file");
//        options.addOption("d", true, "data");
//        CommandLineParser parser = new BasicParser();
//        CommandLine commandLine = parser.parse(options, args);
//        try
//        {
//            if(commandLine.hasOption('f'))
//            {
//                String fileName = commandLine.getOptionValue('f');
//                if(StringUtils.isBlank(fileName))
//                    throw new Exception("fileName is empty");
//                BufferedReader br = new BufferedReader(new FileReader(fileName));
//                do
//                {
//                    String line;
//                    if((line = br.readLine()) == null)
//                        break;
//                    line = StringUtils.trim(line);
//                    if(line.indexOf('#') == -1)
//                    {
//                        String nameValuePair[] = StringUtils.split(line, "=");
//                        if(nameValuePair.length == 2)
//                            method.setParameter(StringUtils.trim(nameValuePair[0]), StringUtils.trim(nameValuePair[1]));
//                    }
//                } while(true);
//            } else
//            if(commandLine.hasOption('d'))
//            {
//                String queryString = commandLine.getOptionValue('d');
//                if(StringUtils.isBlank(queryString))
//                    throw new Exception("data is empty");
//                method.setQueryString(queryString);
//            }
//        	httpclient.e
//            httpclient.execute(httppost);
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//            System.exit(-1);
//        }
//        int ret = 0;
//        if(method.getStatusCode() == 200)
//        {
//            String data = method.getResponseBodyAsString();
//            StringBuffer buf = new StringBuffer();
//            buf.append("InvokeServiceClient ");
//            for(int i = 0; i < args.length; i++)
//                buf.append((new StringBuilder()).append("[").append(args[i]).append("],").toString());
//
//            buf.append((new StringBuilder()).append("[").append(data).append("]").toString());
//            log.info(buf);
//            JSONObject jsonObj = JSONObject.fromObject(data);
//            JSONObject GWAObj = jsonObj.getJSONObject("GWA");
//            String msgCod = null;
//            String msgInf = null;
//            if(GWAObj != null && !GWAObj.isNullObject())
//            {
//                msgCod = (String)GWAObj.get("MSG_CD");
//                msgInf = (String)GWAObj.get("MSG_INF");
//            }
//            if(msgCod == null)
//            {
//                msgCod = (String)jsonObj.get("MSG_CD");
//                msgInf = (String)jsonObj.get("MSG_INF");
//            }
//            if(msgCod == null)
//            {
//                msgCod = (String)jsonObj.get("RSP_CD");
//                msgInf = (String)jsonObj.get("RSP_MSG");
//            }
//            if(msgCod == null)
//                System.out.println("MSG_CD or RSP_CD is empty");
//            else
//            if(isSucc(msgCod))
//            {
//                System.out.println("success");
//            } else
//            {
//                ret = 8;
//                System.out.println((new StringBuilder()).append("failure, ").append(msgCod).append(":").append(msgInf).toString());
//            }
//        } else
//        {
//            String response = method.getResponseBodyAsString();
//            int i = response.indexOf("<pre>");
//            int j = response.indexOf("</pre>");
//            String tmp;
//            if(i != -1 && j != -1 && i < j)
//                tmp = response.substring(i + 5, j - 5);
//            else
//                tmp = response;
//            int k = tmp.indexOf(SystemUtils.LINE_SEPARATOR);
//            if(k != -1)
//                System.out.println(tmp.substring(0, k));
//            else
//                System.out.println(tmp);
//            System.out.println("see SYS.log file for complete information");
//            ret = 8;
//        }
//        method.releaseConnection();
//        System.exit(ret);
    }

    public static boolean isSucc(String code)
    {
        return "000000".equals(code) || code.substring(3).equals("00000");
    }

}

