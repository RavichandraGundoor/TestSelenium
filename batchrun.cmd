set projectPath=%cd%
cd\
cd %projectPath%
set classpath=%projectPath%\bin;C:\Users\Ravi\Desktop\Presentation\Tools\lib\*;
java org.testng.TestNG testng.xml