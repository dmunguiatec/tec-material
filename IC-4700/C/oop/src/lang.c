#include <assert.h>
#include <stdlib.h>

#include "Class_data.h"
#include "lang.h"

void *new(const void *class, ...)
{
    const struct Class *_class = class;
    void *instance = calloc(1, _class->object_size);
    assert(instance);

    // Asignar la clase a la instancia:
    // al castear el puntero de la instancia al tipo
    // puntero de la clase la asignación sólo considera
    // los bytes correspondientes a dicho puntero
    *(const struct Class **)instance = _class;

    if (_class->constructor)
    {
        va_list args;
        va_start(args, class);
        instance = _class->constructor(instance, &args);
        va_end(args);
    }

    return instance;
}

void finalize(void *self)
{
    const struct Class **_class = self;

    if (self && *_class && (*_class)->destructor)
    {
        (*_class)->destructor(self);
    }

    free(self);
}

bool instance_of(const void *self, const void *class)
{
    const struct Class * const *_class = self;
    return same(class, _class);

}

bool same(const void *class, const void *other_class)
{
    return class == other_class;
}
