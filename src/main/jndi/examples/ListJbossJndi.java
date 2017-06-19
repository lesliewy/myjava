package jndi.examples;

import java.io.FileInputStream;
import java.util.Properties;
import javax.naming.*;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * @author shizy
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ListJbossJndi {
	public ListJbossJndi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			Properties env = new Properties();
			env.load(new FileInputStream("jbossJndi.properties"));
			// env.list(System.out);
			Context ctx = new InitialContext(env);
			listCtx(ctx.lookup("sylilzy"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void listCtx(Object o) {
		if (!(o instanceof Context))
			log(":" + o);
		else {
			log("\n-----------------------------");
			try {
				Context ctx = (Context) o;
				// log(ctx.getNameInNamespace()+"/:");
				NamingEnumeration list = ctx.listBindings("");
				while (list.hasMore()) {
					Binding bind = (Binding) list.next();
					log("\n/" + ctx.getNameInNamespace() + "/" + bind.getName());
					listCtx(bind.getObject());
				}
				log("\n-----------------------------");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static void log(Object o) {
		System.out.print(o);
	}
}
