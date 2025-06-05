
# Overview
This program implements traffic simulation and traffic lights management,
based on the roads load.

# Starting the project
Build and run: `sbt "run input.json output.json"`  
Run tests: `sbt test`

# Junction representation
Junction is represented as four roads:
- North road
- East road
- South road
- West road

Each road can have arbitrary many lanes and each lane can have many traffic lights,
representing possible traffic flow direction.  
There are three main traffic flow directions:
- forward
- left
- right

But any subset of these three direction constitutes as a valid traffic flow direction e.g `{forward, left}`
models a lane on which vehicles can either go forward or turn left.  
Aditionally since lane can have more than one traffic light, it is also possible to model lanes with arrows for turning
i.e. `{ {left}, {forward} }` means a lane that has one light for turning left and one for going forward, and these lights are independent from each other.


## Adding vehicle
`addVehicle` command adds a vehcile to the simulation, it has following syntax:
```json
{
      "type": "addVehicle",
      "vehicleId": "vehicle1",
      "startRoad": "south",
      "endRoad": "north"
}
```
New vehicle is pushed to the lane on his starting road. The lane must allow for traffic flow, that would lead this vehicle to its `endRoad`.
If there are many such lanes on the road, the one with minimal load is chosen.

## Traffic Lights

Traffic lights work in standard fashion:
- `green -> yellow`
- `yellow -> red`
- `red -> green`

Vehicles can move when the ligh on their lane is either `green` or `yellow`.  
Additionally the `yellow` light will alway last exactly one simulation step.

## Simulation Step
```json
{
  "type": "step"
}
```

1. Determine traffic lights change.
2. Move all vehicles according to traffic rules that is vehicle can move, only if it is first in lane and the light is not red. 
3. Increment waiting counter for these vehicles, that are still at the junction.

### Determining traffic lights change
#### Traffic compatibility graph
Traffic compatibility graph is a graph in which each vertex represents a possible traffic flow on the junction
and there is edge between two vertices if and only if the flow they represent do not collide with each other, that is they can both have green light at the same time.

Below is figure of such graph for a junction with 3 lanes at each road.
![Traffic compatibility graph](images/g.svg)  
Since there is a lot of edges on the figure, here is the complement graph so the "traffic incompatiblity graph"
![Traffic incompatibility graph](images/nng.svg)  
#### Algorithm
In order to ensure that there won't be any accidents, at any given moment the green light should be on only for lanes that all have edges with each other in the compatibility graph.
This observation boils down to finding cliques in the graph.  
Given list of cliques in the compatibility graph and the junction representation, the algorithm works as follows.
1. Transform the Junction into a list of vertices, ignore empty lanes
2. extract the vertices that already have green light, since changing green light results in yellow light that also allows vehicles to move, we need to ensure that we won't turn green any vertex that collides with the current green ones.
3. Calculate the weight of each clique as a function of "how many vehicles could move, if used this one" and chose the clique with the biggest weight.
4. Iterate over all vertices from step 1. and do
   - if light is green
     - and best clique contains this vertex then skip (stays green)
     - and best clique doesn't contain this vertex: It is not possible since only cliques that "fully intersect" with the green vertices are allowed.
   - if light is yellow then set it to red
   - if light is red
     - and best clique contains this vertex then set it to green
     - and best clique doesn't contain this vertex then skip it (stays red)

Provided that Traffic Lights don't start in some colliding configuration, then this algorithm allows only cliques to move, hence it maintains safety.  
This also means that all Traffic Lights should at first be initialized with red light.

#### Ensuring that each vehicle eventually leaves the junction
There could be a situation when one lane is so overloaded, that a vehicle on some other lane will never get the green light regardless of how long it will wait.  
To avoid such situations, a waiting threshold can be defined when calculating the clique weights - 
that means that vertices with vehicles that waited at first place in lane more than threshold, have a weight of infinity.

## Predefined Junctions configurations

There are two predefined junction configuration:
- `4x_LFR` - four roads, each road has one lane that allows flow `{left, forward, right}`
- `x4_LiFiR` - four roads, each roads has three lanes `{left}`, `{forward}`, `{right}`

# Tests and CI
Tests are perforemed using `scalaTest` package. There are:
- unit tests for `commands` parsing
- integration tests for the whole program, for both junction configurations

For continues integration, GitHub Actions are used.

# Limitations and possible improvements
## Lack of automatic computation of compatibility graph
Traffic compatibility graph is the core concept in mathematical model used for the simulation.
However, the program is not able to generate this kind of graph for an arbitrary crossroad configuration,
which partialy invalidates flexibility and scalability of the code base.


## Lack of automatic calculaction of cliques
As described in the Algorithm section, at the given moment of the simulation,
traffic is only allocated to lanes that formes a clique in the compatiblity graph.  
However these cliques are not generated by the program itself, but have to be put in the code.  
This makes the usage of the application rigid and limited,
since only a selected predefined junctions design are avaialbe for performing the simualtion.  
This point is directly connected with the lack of automatic creation of the compatibility graph since having such graph would allow for cliques computations.

## Loading junction configuration from file
Junction representation in code was designed with possible loading from file in mind. Ulitmately there wasn't enough time to implement this feature.

## Lack of simulation animation
Innitialy, the program was supposed to have graphics module, that would allow for generating `gif` files or displaynig the simulation animation.  
In the code base there is a `graphics` package that uses `Doodle` module to draw arbitrary junction, but it's not finished and is uses different representation for Junction that the one used in Simulation.
In the `main` function there code commented out, that can display the junction as image when uncommented:
```Scala 3
//deprecated.Junction(sr, sr, sr, sr).toImage.transform(Transform.scale(2,2)).draw()
```