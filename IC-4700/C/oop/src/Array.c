#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

#include "lang.h"
#include "Class_data.h"
#include "Object.h"

#include "Array.h"
#include "Array_data.h"
#include "String.h"

static void *Array_constructor(void *self, va_list *args);
static void Array_destructor(void *self);

static bool Array_equals(const void *self, const void *other);
static void *Array_clone(const void *self);
static void *Array_to_string(const void *self);

static const struct Class Array_class = {
    .object_size = sizeof(struct Array),
    .name = "Array",
    .constructor = Array_constructor,
    .destructor = Array_destructor,
    .clone = Array_clone,
    .to_string = Array_to_string,
    .equals = Array_equals,
};

const void *Array = &Array_class;

/**
 * new(Array, String, 10)
 */
static void *Array_constructor(void *self, va_list *args)
{
    struct Array *instance = self;

    const struct Class *content_class = va_arg(*args, struct Class *);
    unsigned int length = va_arg(*args, unsigned int);

    instance->content_class = content_class;
    instance->length = length;
    instance->objects = malloc(length * content_class->object_size);

    return instance;
}

static void Array_destructor(void *self)
{
    struct Array *instance = self;

    for (int i = 0; i < instance->length; i++)
    {
        finalize(instance->objects[i]);
    }

    free(instance->objects);
}

static bool Array_equals(const void *self, const void *other)
{
    const struct Array *instance = self;
    const struct Array *other_instance = other;

    if (self == other)
    {
        return true;
    }

    if (other == NULL ||
        !same(instance->class, other_instance->class))
    {
        return false;
    }

    if (instance->length != other_instance->length)
    {
        return false;
    }

    bool is_equal = true;
    for (int i = 0; i < instance->length && is_equal; i++)
    {
        is_equal = equals(instance->objects[i], other_instance->objects[i]);
    }

    return is_equal;
}

static void *Array_clone(const void *self)
{
    const struct Array *instance = self;

    struct Array *cloned_instance = new (Array,
                                         instance->content_class,
                                         instance->length);

    for (int i = 0; i < instance->length; i++)
    {
        cloned_instance->objects[i] = clone(instance->objects[i]);
    }

    return cloned_instance;
}

static void *Array_to_string(const void *self)
{
    const struct Array *instance = self;

    const char *format = "Array<%s>[%ul]@%p";
    int length = snprintf(NULL, 0, format, instance->content_class->name,
                          instance->length, instance);

    char *buffer = (char *)malloc((length + 1) * sizeof(char));
    snprintf(buffer, length + 1, format, instance->content_class->name,
             instance->length, instance);

    void *string = new(String, buffer);
    free(buffer);

    return string;
}

unsigned int length(void *self)
{
    struct Array *instance = self;
    return instance->length;
}

void *get(void *self, unsigned int index)
{
    struct Array *instance = self;

    assert(index < instance->length);

    return instance->objects[index];
}

void set(void *self, unsigned int index, void *value)
{
    struct Array *instance = self;
    
    assert(index < instance->length);
    assert(same(instance->content_class, *(struct Class **)value));

    instance->objects[index] = value;
}
