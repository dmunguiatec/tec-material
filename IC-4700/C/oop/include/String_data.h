#ifndef STRING_DATA_H
#define STRING_DATA_H

#include <stddef.h>

struct String
{
    struct Class *class;
    size_t length;
    char *chars;
};

#endif