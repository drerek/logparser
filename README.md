Used logs from [here](https://www.secrepo.com/squid/access.log.gz). There are combined from several sources (24MB compressed, ~200MB uncompresed, ~2 million entries) 

Finded artifacts:
* Some logs have so high elapsed_time: 1757834, 1804943, 1873999, 1888555, 2089122, 2121133, 2339909, 2348849, 3006405, 3063406, 3298592, 4284091, 4326808, 6103411, ... with avarage value **52468**
* Some logs have so high byte value: 6280980, 6855807, 7019074, 7019083, 8075522, 9141765, 9273676, 9720298, 10859056, 12265055, 12673821, 14613471, 14617803, 17287286, 66733575, ... with avarage value **42770**
* Periods of activity by hours were parsed be next values 0=59795, 1=68885, 2=73863, 3=63554, 4=63126, 5=58250, 6=53858, 7=54986, 8=62599, 9=63266, 10=92360, 11=84891, 12=65238, 13=58550, 14=54146, 15=53208, 16=48264, 17=69968, 18=75412, 19=80205, 20=74370, 21=82759, 22=122255, 23=59709, 
so the highest value for 22:00-22:59 122255 entries.
