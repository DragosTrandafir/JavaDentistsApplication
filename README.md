# JavaDentistsApplication
Handles a Java application and works with multiple types of files and databases, also offering a JavaFX interface.

----Java solution for managing the appointments to a dentist. The program allows CRUD operations for dentists, adding a new appointment, cancelling an appointment, creating different reports, etc.
IN ORDER TO RUN THE APPLICATION, you have 2 options:
1. run D:\MAP\a5-2024-DragosTrandafir\src\gui\DentistsAndAppointmentsGUI.java -> JavaFXML user interface
2. run D:\MAP\a5-2024-DragosTrandafir\src\main\Main.java                      -> console interface 


OTHER DETAILS:
-	All entities are identifiable (use a superclass/interface Identifiable) and unique.
-	There is provided a generic interface for the repository and an implementation for a generic memory repository which stores identifiable objects in a Map (HashMap, TreeMap), where the objects identifiers are the keys and the values are the actual objects.(see first UML diagram)
-	Entities are filtered by various criteria. A generic AbstractFilter interface is used and this interface is implemented in various classes, according to the required filters. A generic FilteredRepositoy can use any filtering strategy and this is done for each of the entities.(see first uml diagram)



-	Repositories storing and retrieving data in different formats are used: text files, binary files (using the Java serialization mechanism), json files, xml files and also databases. The program works identically any repository corresponding to the type of file/database. The decision of which repositories are employed, as well as the location of the repository input files is made available via the program’s **settings.properties** file and the Java *Properties* class. Also, we can decide which repositories are taken from file/database and which for memory. (for example, if we have appointments:no and dentists:yes , it means that the type is used for dentists and appointments will be a MemoryRepository(normal repository). Of course, if we have no for both or if we have at type anything else than text, binary, json,xml, database, MemoryRepository will be used for both appointments and dentists).
- There are custom Validator classes to validate inputs. Validator objects are provided to my service(s).
- There are provided different reports







