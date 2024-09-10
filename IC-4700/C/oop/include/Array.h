#ifndef ARRAY_H
#define ARRAY_H

extern const void *Array;

unsigned int length(void *self);
void *get(void *self, unsigned int index);
void set(void *self, unsigned int index, void *value);

#endif