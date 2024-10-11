#include <assert.h>
#include <stdbool.h>

#include "Class_data.h"
#include "Object.h"

bool equals(const void *self, const void *other)
{
    const struct Class * const *_class = self;
    assert(self && *_class && (*_class)->equals);

    return (*_class)->equals(self, other);
}

void *clone(const void *self)
{
    const struct Class * const *_class = self;
    assert(self && *_class && (*_class)->clone);

    return (*_class)->clone(self);
}

void *to_string(const void *self)
{
    const struct Class * const *_class = self;
    assert(self && *_class && (*_class)->to_string);

    return (*_class)->to_string(self);
}