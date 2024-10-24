# Minimum CMake version
cmake_minimum_required(VERSION 3.29)

# Project name
project(Particles)

include_directories(include)

# Set the C++ standard
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED True)

# Add the executable
add_executable(Particles main.cpp
        include/Particle.h
        src/Particle.cpp)

# Find the SFML package
find_package(SFML 2.5 COMPONENTS graphics window system REQUIRED)

# Link the SFML libraries to the executable
target_link_libraries(Particles sfml-graphics sfml-window sfml-system)

