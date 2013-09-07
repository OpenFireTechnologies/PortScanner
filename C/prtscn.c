#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <sys/signal.h>
#include <netinet/in.h>
#include <netdb.h>
#include <errno.h>
#include <unistd.h>

#define DEBUGMODE 1

/* Method to log, to hide the messages, set DEBUGMODE above to 0 */
int logDebug(char* message)
{
	if(DEBUGMODE)
	{
		printf("[!] %s\n", message);
	}
}

int main(int argc, char *argv[])
{
   	int sck; 
	int port_start;
	int port_end;
	int current_port;

   	struct hostent *he;
   	struct sockaddr_in addr;
   	struct servent *serv;

      	printf("Port Scanner v0.1\n");

	//Check if 3 arguments are entered
      	if(argc == 4)
	{
		logDebug("argc is 4");
		port_start = atoi(argv[2]);
		port_end = atoi(argv[3]);
	}
	//Check if less then 3 are entered
	if(argc < 4)
	{
		logDebug("argc is LESS 4");
		if(strcmp(argv[2], "all") == 0)
		{
			logDebug("all is set");
			port_start = 1;
			port_end = 65535;
		}
		else
		{
			printf("Usage: %s IP startport endport\n", argv[0]);
			printf("Usage: %s IP all - to scan all ports\n", argv[0]);
			exit(1);
		}
	}
	logDebug("Initializing done.\n");

	//======================
	//== Initializing done =
	//======================

	//saving current time for later comparison when scan is finished
   	long t = time(0);

	//loop through each port defined in a range
   	for(current_port = port_start; current_port <= port_end; current_port++) 
	{
		//check if the IP/hostname is valid
    		if((he = gethostbyname(argv[1])) == NULL) 
		{
      			printf("Invalid IP!\n");
      			exit(1);
    		}
		
		//initialize AF_INET socket 
    		if((sck = socket(AF_INET,SOCK_STREAM,0)) < 0) 
		{
      			perror("Socket error!\n");
      			exit(2);
    		}
		
		//fill out the struct :P
    		addr.sin_family = he->h_addrtype;
    		addr.sin_port = htons(current_port);
    		addr.sin_addr = *((struct in_addr *) he->h_addr);

		//zero out the first 8 bytes from the sockaddr_in structure to avoid crashes and misbehaviour on older systems.
    		bzero(&(addr.sin_zero), sizeof(struct sockaddr_in)/2);

		//try to connect to a desired port, if port is found open, proceed to "else", else close the socket.
    		if(connect(sck, (struct sockaddr *)&addr, sizeof(addr)) < 0) 
		{
      			close(sck);
    		}

    		else 
    		{	
			//assign the found port its service name
    			serv = getservbyport(htons(current_port), "tcp");
    			printf("Port: %d \t State: Open \t Service: %s\n",current_port,(serv == NULL) ? "Unknown" : serv->s_name);
    		}
    		close(sck);
  	}
 
	printf("\nScan completed in %lu seconds.\n", time(0)-t);
 	return 0;
}




