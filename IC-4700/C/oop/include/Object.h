#ifndef OBJECT_H
#define OBJECT_H

#include <stdbool.h>

bool equals(const void *self, const void *other);
void *clone(const void *self);
void *to_string(const void *self);

#endif