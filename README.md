# JavaDentistsApplication
Handles a Java application and works with multiple types of files and databases, also offering a JavaFX interface.

Requirements:
Design and implement a Java solution for managing the appointments to a dentist. The program should allow CRUD operations for dentists, adding a new appointment, cancelling an appointment, creating different reports, etc.


-	All entities should be identifiable (use a superclass/interface Identifiable) and unique.
-	Provide a generic interface for the repository and an implementation for a generic memory repository which stores identifiable objects in a Map (HashMap, TreeMap), where the objects identifiers are the keys and the values are the actual objects (please see the UML diagram in the given image).
-	Filter your entities by various criteria (2 criteria for each entity). Use a generic AbstractFilter interface and implement this interface in various classes, according to the required filters. Define a generic FilteredRepositoy which can use any filtering strategy and then extend this for each of your entities (please see the UML diagram in the given image).
-	Add basic data validation and use the exception mechanism in Java for exceptional situations. Show messages in case of such situations.
-	The UI must allow CRUD operations for both entities.





