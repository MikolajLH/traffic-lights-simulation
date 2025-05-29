# Draft and notes

# Overview
Simulation is a sequence of states  
$$
(S_1, S_2, \ldots, S_n)
$$
where the transformation from one state to another is caused by applying some command  
$$
S_{i+1} = C(S_i)
$$
where command is a function that maps simulation state to another simulation state.

More about the state, it seems to me that state consists of two components:
1. Traffic lights - mapping of a type `Route -> Bool` that indicates which route can be taken.
2. Vehicles - a list of all vehicles currently on the junction.

## Commands overview
1. `AddVehicle` - seems straightforward - just push the `(id, from, to)` to the vehicles list.
2. `Step` - consists of two parts:
   1. `UpdateTrafficLights` - the essence of the application, based on the vehicles list and current traffic lights produces new traffic lights -  `(Vehicles, TrafficLights) -> TrafficLights`
   2. `VehiclesGo` - vehicles move according to the traffic lights, produces a list of vehicles that has just left the junction.

## TODO:
- [ ] Design sensible types and types structure.
- [ ] Implement `InputParser` that can map input json file to a sequence of commands.
- [ ] Implement basic simulation without any rules.
- [ ] Make custom config file format for loading a junction.
- [ ] Load the junction from config file.
- [ ] Specify what kind of rules have to always be in place for the traffic lights state.
- [ ] Implement some form of an intelligent algorithm for the traffic lights.
- [ ] Consider adding GUI or generating a gif file as the additional output.
