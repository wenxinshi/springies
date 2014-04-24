#Design
####Michael Marion, Steven Shi, Alex Song

This design document outlines the various classes, state, and methods we envision writing for the Springies project.

##Inheritance Hierarchy


##Classes
To accord with the JEngine, each class listed below will extend the PhysicalObject class to inherit the move(), setPos(), and setForce() methods, among other useful functionality. We will then define specific behaviors within each class.

###Mass
This class will represent "mass" objects in our simulation. Masses will have the following information represented as instance variables:

- ID (String)
- Mass (int/double)
- X-Coordinate (double)
- Y-Coordinate (double)
- X-Velocity (float)
- Y-Velocity (float)
- Collision ID (int)
- Spring(s) to which the mass is attached (Set)

The mass will have a hit() method to describe how it collides with other masses. Removing this from the Springies class will also be helpful, because inclusion of the hit() in the Springies class is misleading, possibily suggesting that springs and muscles are also capable of collision.

Move()-ing a mass involves calling methods in the Spring and Force classes to calculate the proper total force acting on the mass. We believe that Masses are the only objects that will truly "move" in the simulation, as springs simply react to the masses to which they are attached.

####FixedMass
This class will extend the Mass class. Specifically, it will override its move() method to prevent the object from ever moving. We'll have this overriden method "do nothing" to ensure that this happens. However, we anticipate the possibility of other objects hitting the mass; thus, we will keep the hit() method intact to handle this scenario, though it will be modified slightly to account for the fact that this mass "knows" that it is fixed in place.

Otherwise, this class has the same characteristics as its parent class.

###Spring
This class will represent "spring" objects in our simulation. Springs will have the following information represented as instance variables:

- Two Masses (A and B) that reside at the endpoints of the spring
- A current length (double)
- A rest length (double)
- A spring constant (float)

Springs will provide this information to the Mass class, since springs will not move on their own inherently; i.e. Masses drive all of the real movement in the simulation.

Springs do not collide, so a hit() method will not be necessary for the spring class.

####Muscle
Muscles are similar to springs, though they are constantly being acted upon by the forces of a given wave. As such, Muscles will extend the Spring class and query the Wave class for information on how to update their own length and so forth. 

When a mass is move()-ed, it will ask Springs to which it is attached for their current length, rest length, and spring constant to calculate Hooke's Law. A Muscle will constantly update its own information with help from the Wave class, so that this information is representative of the ever-changing nature of a muscle.

###Wave
This class will be separate from the muscle class, and wiill be a singleton instance that represents the same waveform for all muscles. These waves will have certain properties, as shown in the SodaPlay Constructor:

- Amplitude (double)
- Speed (double)

Originally, our idea was to include the wave properties within the muscle class; however, we realized that it might be a better idea to make the Wave a separate class. Instantiating each muscle with its own wave properties would in essence create a new wave for every muscle — we believe that abnormal behavior would result should the properties of these waves not be identical. We want to instantiate a single wave that holds information accessible to all muscles.

###Force
Forces share the following properties:

- Magnitude
- Direction

However, these wiil be specified by their respective subclasses detailed below. Thus, this will be an abstract class and will not represent any particular force. This class will include getters and setters for subclasses and Masses that are moving to access magnitude and direction. Each subclass will also inherit a calculateForce() method, which will be called by a move()-ing mass and will provide the mass with a calcuation based on the properties of the specific force.

####Gravity
Gravity represents a constant downward force of 9.81m/s.

####Viscosity
The direction of viscosity would be the opposite of the direction of the mass calling the class, and magnitude would be represented by a constant factor.

####Center of Mass
This class will average the positions and masses of all masses in the simulation and will apply a subtle force to each mass that slowly brings them to the center of mass.

####Wall Repulsion
We think of this force as the "bounciness" of a given wall. Thus, this class will use a constant to calculate how hard objects should rebound off of a wall.

###Wall
The Wall class will extend the PhysicalObjectRect class. In examining the starter code, we noticd that walls were made with repeated similar calls to the PhysicalObjectRect constructor. We believe that making a Wall class will clean up this part of the code and will be easier to manage down the road as code grows.
