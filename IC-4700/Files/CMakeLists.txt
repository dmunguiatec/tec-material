cmake_minimum_required(VERSION 2.8.4)
project(Files)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c11")

set(SOURCE_FILES main.c)
add_executable(Files ${SOURCE_FILES})
add_definitions(-std=c11)