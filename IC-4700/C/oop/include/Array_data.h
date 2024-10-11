#ifndef ARRAY_DATA_H
#define ARRAY_DATA_H

#include <stddef.h>

struct Array
{
    struct Class *class;
    const struct Class *content_class;
    size_t length;
    void **objects;
};

#endif