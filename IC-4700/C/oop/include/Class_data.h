#ifndef CLASS_DATA_H
#define CLASS_DATA_H

#include <stdarg.h>
#include <stdbool.h>
#include <stddef.h>

struct Class
{
    size_t object_size;
    const char *name;
    void *(*constructor)(void *self, va_list *args);
    void (*destructor)(void *self);
    bool (*equals)(const void *self, const void *other);
    void *(*clone)(const void *self);
    void *(*to_string)(const void *self);
};

#endif