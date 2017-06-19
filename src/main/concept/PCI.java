package concept;
interface PCI
{
	void start();
	void stop();
}
class NetworkCard implements PCI
{
	public void start()
	{
		System.out.println("Send ...");
	}
	public void stop()
	{
		System.out.println("Network Stop.");
	}
}

class SoundCard  implements PCI
{
	public void start()
	{
		System.out.println("Du du...");
	}
	public void stop()
	{
		System.out.println("Sound Stop.");
	}
}
//上面必须写明 implements 的是哪个接口，否则这里不能直接调用.
class MainBoard 
{
	public void usePCICard(PCI p)
	{
		p.start();
		p.stop();
	}
}
/*
 * 1,匿名内置类.
 * 
 */
class Assembler
{
	public static void main(String [] args)
	{
		MainBoard mb=new MainBoard();
		NetworkCard nc=new NetworkCard();
		mb.usePCICard(nc);
		SoundCard sc=new SoundCard();
		mb.usePCICard(sc);
		mb.usePCICard(new PCI(){
			public void start()
			{
				System.out.println("Send ...");
			}
			public void stop()
			{
				System.out.println("Network Stop.");
			}
		});
	}
}
