#include <stdio.h>
#include <string.h>


#define BUFFER_SIZE 1025

void process_file(FILE* p_file) {
    char buffer[BUFFER_SIZE];

    size_t count;
    while((count = fread(buffer, sizeof(char), BUFFER_SIZE - 1, p_file))) {
        buffer[count] = '\0';
        printf("%s", buffer);
    }
}

int main(int argc, char* argv[]) {
    FILE* file = fopen(argv[1], "r");
    process_file(file);
    fclose(file);

    return 0;
}