#-------------------------------------------------------------------------------
# Name:        Port_Scanner_With_Color.py
# Purpose:      Port Scanner
#
# Author:      Shubham Raj ( http://www.facebook.com/xceptioncode)
#
# Website:      http://www.openfire-security.net
# Forum:        http://www.openfire-seucirty.net
#
# Created:    02/09/2013
# Copyright:   (c) Xception 2013
# Licence:     Open Source
#-------------------------------------------------------------------------------

import socket, time, sys
try:
	from colorama import init, Fore, Back, Style
except ImportError:
	print "You need Coloroma module to run this file"
	sys.exit()	

init()

print "\n\n"+ Fore.WHITE + Style.DIM +""
print "\t\t________                       ___________.__"
print "\t\t\_____  \ ______   ____   ____ \_   _____/|__|______   ____ "
print "\t\t /   |   \\____ \_/ __ \ /    \ |    __)  |  \_  __ \_/ __ \ "
print "\t\t/    |    \  |_> >  ___/|   |  \|     \   |  ||  | \/\  ___/ "
print "\t\t\_______  /   __/ \___  >___|  /\___  /   |__||__|    \___  >"
print "\t\t        \/|__|        \/     \/     \/                    \/"
print "\t\t" + Fore.RED + Style.BRIGHT +"                                           Port Scanner\n\n"+ Fore.WHITE + Style.DIM +""

def help():
    print "" + Fore.GREEN + Style.BRIGHT +"[=] Usage : port_scanner.py IP Start_Port End_Port"
    print "[=] Example : port_scanner.py 123.222.212.222 0 80" + Fore.WHITE + Style.DIM + ""
    exit()

if len(sys.argv) < 2:
    help()
elif sys.argv[1] == "--help":
    help()
elif len(sys.argv) < 4:
    print "[=] Error Occured"
    help()
else:
    pass

try:
    ip = sys.argv[1]
    start_port = sys.argv[2]
    end_port = sys.argv[3]
except:
    print "[=] Error Occured\n"
    help()

def port_scanner(ip, start_port, end_port):

    start = time.time()
    print "Scanning started at %s \n" %start
    for n in range(start_port, end_port+1):
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        try:
            result = sock.connect_ex((ip, n))
        except KeyboardInterrupt:
            print "\nYou pressed Ctrl+C, Stopping Program.\n"
            sys.exit()
        except socket.gaierror:
            print 'Hostname could not be resolved. Exiting\n'
            sys.exit()
        except socket.error:
            print "Couldn't connect to server\n"
            sys.exit()
        except Exception as e:
            print e
            continue
        if result == 0:
            print "[#] Port "+ Fore.GREEN  + Style.BRIGHT +"%d " % n+ Fore.WHITE + Style.DIM + "is " + Fore.GREEN  + Style.BRIGHT +"OPEN"+ Fore.WHITE + Style.DIM + ""
        elif result == 10061:
            print "[=] Port "+ Fore.RED  + Style.BRIGHT +"%d " % n+ Fore.WHITE + Style.DIM + " is "+ Fore.RED  + Style.BRIGHT +"CLOSED" + Fore.WHITE + Style.DIM + ""
        elif result == 10035:
            print "[#] Port "+ Fore.YELLOW  + Style.BRIGHT +"%d " % n+ Fore.WHITE + Style.DIM + " is "+ Fore.YELLOW  + Style.BRIGHT +"FILTERED"+ Fore.WHITE + Style.DIM + ""
        sock.close()
    end = time.time()
    total = end-start
    print "" + Fore.WHITE + Style.DIM +"\nScanning completed at %s.\nTotal time taken is %s seconds" % (end, total)

try:
    port_scanner(ip, int(start_port), int(end_port))
except ValueError:
    print "\n[=] Port must be an Integer"