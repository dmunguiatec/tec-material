#ifndef LANG_H
#define LANG_H

#include <stdbool.h>

void *new(const void *class, ...);
void finalize(void *instance);
bool instance_of(const void *self, const void *class);
bool same(const void *class, const void *other_class);

#endif