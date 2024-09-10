CC = gcc
CFLAGS = -I./include -ggdb -std=c17 -Wall

SRC_DIR = ./src
OBJ_DIR = ./obj
BIN_DIR = ./bin

SRCS = $(wildcard $(SRC_DIR)/*.c)
OBJS = $(SRCS:$(SRC_DIR)/%.c=$(OBJ_DIR)/%.o)
TARGET = $(BIN_DIR)/byooop

all: $(OBJ_DIR) $(BIN_DIR) $(TARGET)

$(TARGET): $(OBJS)
	$(CC) -o $@ $^

$(OBJ_DIR)/%.o: $(SRC_DIR)/%.c
	$(CC) $(CFLAGS) -c -o $@ $<

clean:
	rm -f $(OBJ_DIR)/*.o $(TARGET)

vg:
	valgrind --leak-check=full --show-leak-kinds=all --track-origins=yes --verbose $(TARGET)

.PHONY: all clean vg