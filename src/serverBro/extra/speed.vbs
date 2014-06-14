Option Explicit 
On Error Resume Next
dim strComputer
dim wmiNS
dim wmiQuery
dim objWMIService
Dim objLocator
dim colItems
dim objItem
Dim strUsr, strPWD, strLocl, strAuth, iFLag 'connect server parameters

wmiNS = "\root\cimv2"
wmiQuery = "Select BytesReceivedPerSec, BytesSentPerSec from Win32_PerfFormattedData_Tcpip_NetworkInterface"

Set objLocator = CreateObject("WbemScripting.SWbemLocator")
Set objWMIService = objLocator.ConnectServer(strComputer, _
     wmiNS, strUsr, strPWD, strLocl, strAuth, iFLag)
Set colItems = objWMIService.ExecQuery(wmiQuery)

Wscript.Echo ";"
For Each objItem in colItems
 
   Wscript.Echo "@"
   Wscript.Echo "interface: " & objItem.name
   Dim received
   Dim sent 

   sent =  formatNumber(objItem.BytesSentPerSec) & " B/s"
   received   = formatNumber(objItem.BytesReceivedPerSec) & " B/s"
   Wscript.Echo "received: " &  sent
   Wscript.Echo "sent:     " & received
Next
