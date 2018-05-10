cmake_minimum_required(VERSION 2.8.4)
project(Threads C)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS}")

set(SOURCE_FILES main.c)
add_executable(Threads ${SOURCE_FILES})
find_package (Threads)
target_link_libraries (Threads ${CMAKE_THREAD_LIBS_INIT})
add_definitions(-std=c11)

