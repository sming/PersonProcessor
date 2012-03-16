Person Processor README
Peter Kingswell
03/16/2012
===============================================================================
To run the person processor:
C:\eclipse\Cyrus>java -jar bin\processor.jar domain_files\pipe.txt domain_files\comma.txt domain_files\space.txt

To execute the CSV Person factory test case:
C:\eclipse\Cyrus>java -cp bin;bin\junit.jar;bin\processor.jar;bin\org.hamcrest.core_1.1.0.v20090501071000.jar org.junit.runner.JUnitCore org.psk.cyrus.test.CSVPFTest

To execute the file output test case:
C:\eclipse\Cyrus>java -cp bin;bin\junit.jar;bin\processor.jar;bin\org.hamcrest.core_1.1.0.v20090501071000.jar org.junit.runner.JUnitCore org.psk.cyrus.test.OutputTest

