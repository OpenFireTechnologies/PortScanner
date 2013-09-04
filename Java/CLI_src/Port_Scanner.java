import java.io.*;
import java.util.*;
import java.net.*;
class Port_Scanner
{
static long start=0;
static long stop=0;
static float timetaken=0;
static int port=0;
static int limit=0;
static ArrayList <Integer>open_ports;

//FUNCTION TO DISPLAY BANNER-----------------------
public static void BANNER()
{
System.out.print(  "    >===>                                      >=======>                         \n"+                   
                   "  >=>    >=>                                   >=>       >>                      \n"+
                   ">=>        >=> >=> >=>    >==>     >==>> ==>   >=>             >> >==>   >==>    \n"+ 
                   ">=>        >=> >>   >=> >>   >=>    >=>   >=>  >=====>   >=>    >=>    >>   >=>  \n"+ 
                   ">=>        >=> >>   >=> >>===>>=>   >=>   >=>  >=>       >=>    >=>    >>===>>=> \n"+ 
                   "  >=>     >=>  >=> >=>  >>          >=>   >=>  >=>       >=>    >=>    >>        \n"+ 
                   "    >===>      >=>       >====>    >==>   >=>  >=>       >=>   >==>     >====>   \n"+ 
                   "               >=>                                                               \n");

System.out.print(  "                   ::::::'##:::'###:::'##::::'##:::'###:::: \n"+
                   "                   :::::: ##::'## ##:::##:::: ##::'## ##::: \n"+
                   "                   :::::: ##:'##:. ##::##:::: ##:'##:. ##:: \n"+
                   "                   :::::: ##'##:::. ##:##:::: ##'##:::. ##: \n"+
                   "                   :##::: ##:#########. ##:: ##::#########: \n"+
                   "                   :##::: ##:##.... ##:. ## ##:::##.... ##: \n"+
                   "                   . ######::##:::: ##::. ###::::##:::: ##: \n"+
                   "                   :......::..:::::..::::...::::..:::::..:: \n");

System.out.print(  "######                          #####                                            \n"+
                   "#     #  ####  #####  #####    #     #  ####    ##   #    # #    # ###### #####  \n"+
                   "#     # #    # #    #   #      #       #    #  #  #  ##   # ##   # #      #    # \n"+
                   "######  #    # #    #   #       #####  #      #    # # #  # # #  # #####  #    # \n"+
                   "#       #    # #####    #            # #      ###### #  # # #  # # #      #####  \n"+ 
                   "#       #    # #   #    #      #     # #    # #    # #   ## #   ## #      #   #  \n"+
                   "#        ####  #    #   #       #####   ####  #    # #    # #    # ###### #    # \n\n");

System.out.println(" {*}                       OpenFire Java Port Scanner                      {*} \n"+
                   " {*}             Developed by:- Supratik Banerjee (drakula941)             {*} \n"+ 
                   " {*}             Homepage:- http://www.openfire-security.net/              {*} \n"+
                   " {*}                           Version:- 1.1                               {*} \n\n");
}


//MAIN FUNCTION-----------------------
public static void main(String args[])throws IOException
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
InetAddress rslt=null;
String IP="";
String opt="";
do
{
BANNER();
open_ports=new <Integer>ArrayList();
try
{
//IP input is taken from the user
System.out.println("Enter the IP address");
IP=br.readLine();
if(IP!=null)
{
//IP passed to the function 'scan()'
rslt=InetAddress.getByName(IP);
SCAN(rslt);
}
}
catch(UnknownHostException ee)
{
System.out.println("Error in IP");
}
//waitsfor user input to reuse the scanner or exit
System.out.println("'Y' to reuse || 'N' to exit");
opt=br.readLine();
}
while(opt.equalsIgnoreCase("Y"));
}


//FUNCTION TO SCAN PORTS-----------------------
public static void SCAN(final InetAddress ret)throws IOException
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//port input from user
System.out.println("Enter the Initial limit of port ");
port=Integer.parseInt(br.readLine());
System.out.println("Enter the end limit of port");
limit=Integer.parseInt(br.readLine());
//ticks the start time to calculate total time taken for scanning
start=System.currentTimeMillis();
while(port<=limit)
{
try
{
//scans ports
Socket s1=new Socket(ret,port);
s1.setSoTimeout(1000);
System.out.println("Port is listening at port :"+port);
open_ports.add(port);
}
catch(IOException ex)
{
System.out.println("Port is not listening at port :"+port);
}
port++;
}
//ticks the end time to calculate total time taken for scanning
stop=System.currentTimeMillis();
//function port info is called
PORTINFO();
}


//FUNCTION TO SHOW ALL IFORMATION REGARDING PORTS-----------------------
public static void PORTINFO()
{
System.out.println("______________________________\n");
timetaken=((stop-start)/1000);
System.out.println("Ports Scanned :"+(port-1));
System.out.println("Time Taken to scan :"+timetaken+" sec");
System.out.println("No. of Ports Close :"+((port-1)-open_ports.size()));
System.out.println("No. of Ports Open :"+open_ports.size());
System.out.println("______________________________\n");
for(int i=0;i<open_ports.size();i++)
{
System.out.println("[+] Port OPEN :"+open_ports.get(i));
}
System.out.println("______________________________\n");
} 
}
