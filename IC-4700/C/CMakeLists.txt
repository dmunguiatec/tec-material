cmake_minimum_required(VERSION 3.6)
project(C)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c11")

set(SOURCE_FILES addresses.c)
add_executable(C ${SOURCE_FILES})