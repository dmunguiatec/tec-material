#ifndef STRING_H
#define STRING_H

#include <stdbool.h>

extern const void *String;

char char_at(void *self, int index);
void *concat(void *self, void *other);
bool starts_with(void *self, void *prefix);
bool ends_with(void *self, void *suffix);
bool is_empty(void *self);
unsigned int index_of(void *self, void *substring);
unsigned int last_index_of(void *self, void *substring);
void *split(void *self, char delimiter);
void *substring(void *self, unsigned int start, unsigned int end);
char *to_char_array(void *self);
void *to_lower(void *self);
void *to_upper(void *self);
void *trim(void *self);
void print(void *self);
void println(void *self);

#endif