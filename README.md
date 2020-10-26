<h1> <img src="https://cloud.githubusercontent.com/assets/5456665/21795585/18e593d6-d72d-11e6-80dc-c1aba7956d35.png" width="20" height=auto /> ResultAnalysisTool </h1>

#### A Complete Package for Processing OMR Answer Sheet 

Result Analysis Tool (RAT) is a software for examining scanner educed image of OMR sheet, constructing database, data correction, marking, report generation, and result delivery.

### Usage Scenario

In an MCQ exam students answer through OMR sheet which goes into a scanner. After processing each sheet the machine outputs extracted data as an image file. The main answer file (contains right answers) is also prepared in this way, only here the OMR sheet is filled up by the teachers. This will be later compared with the student answer file to make the result.

At first, we shall define areas of an answer sheet (scanned image) and get an output file in Image Definition Graph (IDG) format. In the process indicator areas and answer areas are defined. Now, using this file we shall process all image files, including main answer sheet, and extract their data as a Comma Separated Data (CSD) file.

Secondly, a CSD file (main or student answer file) is used to make the Table Attribute Information (TAI) file. In the process the user defines field names (like- class, roll, set code, answer field and so on), their respective range as well as answer fields’ values (number add/subtract for correct/incorrect answer). Then student answer file (in CSD format) is imported which uses TAI file as standard and a Data Base Format (DBF) file is made.

Now, the user will be able to perform various operations like- searching, sorting and listing; over student answer file (DBF) for error correction like- wrong roll number, registration number, set code, section and so on. After that user will compare the file with main answer file (CSD format) and use Table Attribute Information file to obtain initial result. User may also insert additional data like- number of other subjects, or calculate grade and so forth; and finally produce the complete result.

On this point, all is left is to attach additional information (name, father/mother name, age, institute, address and so forth) from outer database with the result and produce the final result.

Now, if necessary the user will be able to generate individual result, merit list, waiting list. The main output will be published as report. Before that user designs the report and creates a Report Definition Graph (RDG). In the process he defines text area, image area & table area.

User can directly print or email the result as well. Moreover, he can convert the data as XLS or PDF format too.

### Demonstration

 - [Video Demonstration](https://drive.google.com/open?id=0B54vrHge-bxdfmx5OGFjYmxXdmhNUUZqU3Z1Y19uOHdqX3dKR2hJUnQzSUtaZC1LWkVnZlE)

### Documentation

 - Project Proposal- [document](http://www.slideshare.net/100005232690054/project-proposal-result-analysis-tool), [slide](https://www.slideshare.net/100005232690054/project-proposal-result-analysis-tool-55262184)
 - Software Requirement Specification- [document](http://www.slideshare.net/100005232690054/software-requirement-specification-on-result-analysis-tool)

### Download Demo-Version

 - Release Year 2015- [demo V2](https://github.com/MinhasKamal/ResultAnalysisTool/blob/master/bin/ResultAnalysisToolDemo%20(v2).jar?raw=true)
 - Release Year 2014- [demo V1](https://github.com/MinhasKamal/ResultAnalysisTool/blob/master/bin/ResultAnalysisToolDemo%20(v1-ActiveNumberTable).jar?raw=true)

### Project Source

- [Source Code](https://bitbucket.org/MinhasKamal/resultanalysistool)

### Developers

<table>
<tr> <td><img src="https://avatars0.githubusercontent.com/u/5456665?v=3&s=460" height="50" width=auto /></td> <td><a href="https://minhaskamal.github.io">Minhas Kamal</a></td> </tr>
<tr> <td><img src="https://avatars1.githubusercontent.com/u/22680802?v=3&s=460" height="50" width=auto /></td> <td><a href="https://github.com/ishmam-shah">Shahriar Mohammed Ishmam</a></td> </tr>
<tr> <td><img src="https://avatars2.githubusercontent.com/u/5452170?v=3&s=460" height="50" width=auto /></td> <td><a href="https://github.com/Mamunahmed33">Khandaker Mamun Ahmed</a></td> </tr>
<tr> <td><img src="https://pbs.twimg.com/profile_images/661423116954918912/MJo5ynGG_400x400.jpg" height="50" width=auto /></td> <td><a href="https://github.com/MostaqueAhmed">Mostaque Ahmed</a></td> </tr>
<tr> <td><img src="https://avatars0.githubusercontent.com/u/5450101?v=3&s=460" height="50" width=auto /></td> <td><a href="https://github.com/saif0524">Saif Uddin Mahmud</a></td> </tr>
</table>

### Supervisor

<table>
<tr> <td><img src="https://avatars2.githubusercontent.com/u/4959307?s=400&v=4" height="50" width=auto /></td> <td><a href="https://github.com/sarwar187">
Sheikh Muhammad Sarwar</a></td> </tr>
</table>
