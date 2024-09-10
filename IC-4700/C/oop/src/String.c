#include <assert.h>
#include <ctype.h>
#include <stdarg.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "lang.h"
#include "Class_data.h"

#include "String.h"
#include "String_data.h"

#include "Array.h"

#define STR_DIF(str1, str2) (strcmp(str1, str2) != 0)
#define STR_EQ(str1, str2) (strcmp(str1, str2) == 0)

static const int NOT_FOUND = -1;

static void *String_constructor(void *self, va_list *args);
static void String_destructor(void *self);

static bool String_equals(const void *self, const void *other);
static void *String_clone(const void *self);
static void *String_to_string(const void *self);

static const struct Class String_class = {
    .object_size = sizeof(struct String),
    .name = "String",
    .constructor = String_constructor,
    .destructor = String_destructor,
    .clone = String_clone,
    .to_string = String_to_string,
    .equals = String_equals,
};

const void *String = &String_class;

/**
 * new(String, "Hello World!")
 */
static void *String_constructor(void *self, va_list *args)
{
    struct String *instance = self;

    const char *chars = va_arg(*args, char *);
    instance->length = strlen(chars);
    instance->chars = malloc(instance->length + 1);
    strcpy(instance->chars, chars);

    return instance;
}

static void String_destructor(void *self)
{
    struct String *instance = self;

    free(instance->chars);
}

static bool String_equals(const void *self, const void *other)
{
    const struct String *instance = self;
    const struct String *other_instance = other;

    if (self == other)
    {
        return true;
    }

    if (other == NULL ||
        !same(instance->class, other_instance->class))
    {
        return false;
    }

    return STR_EQ(instance->chars, other_instance->chars);
}

static void *String_clone(const void *self)
{
    const struct String *instance = self;

    return new (String, instance->chars);
}

static void *String_to_string(const void *self)
{
    return String_clone(self);
}

char char_at(void *self, int index)
{
    struct String *instance = self;

    assert(index < instance->length);

    return instance->chars[index];
}

void *concat(void *self, void *other)
{
    struct String *instance = self;
    struct String *other_instance = other;

    int length = instance->length + other_instance->length;
    char *chars = malloc(length + 1);
    strcpy(chars, instance->chars);
    strcat(chars, other_instance->chars);

    void *concatenated = new (String, chars);

    free(chars);

    return concatenated;
}

bool starts_with(void *self, void *prefix)
{
    struct String *instance = self;
    struct String *prefix_string = prefix;

    if (instance->length < prefix_string->length)
    {
        return false;
    }

    return strncmp(instance->chars,
                   prefix_string->chars,
                   prefix_string->length) == 0;
}

bool ends_with(void *self, void *suffix)
{
    struct String *instance = self;
    struct String *suffix_string = suffix;

    if (instance->length < suffix_string->length)
    {
        return false;
    }

    return strncmp(instance->chars + instance->length - suffix_string->length,
                   suffix_string->chars,
                   suffix_string->length) == 0;
}

bool is_empty(void *self)
{
    struct String *instance = self;
    return instance->length == 0;
}

unsigned int index_of(void *self, void *substring)
{
    struct String *instance = self;
    struct String *substring_string = substring;

    char *substring_chars = substring_string->chars;
    char *chars = instance->chars;
    int substring_length = substring_string->length;
    int length = instance->length;

    for (int i = 0; i <= length - substring_length; i++)
    {
        if (strncmp(chars + i, substring_chars, substring_length) == 0)
        {
            return i;
        }
    }

    return NOT_FOUND;
}

unsigned int last_index_of(void *self, void *substring)
{
    struct String *instance = self;
    struct String *substring_string = substring;

    char *substring_chars = substring_string->chars;
    char *chars = instance->chars;
    int substring_length = substring_string->length;
    int length = instance->length;

    for (int i = length - substring_length; i >= 0; i--)
    {
        if (strncmp(chars + i, substring_chars, substring_length) == 0)
        {
            return i;
        }
    }

    return NOT_FOUND;
}

void *split(void *self, char delimiter)
{
    struct String *instance = self;
    char *chars = instance->chars;
    int length = instance->length;
    
    int token_count = 1;
    for (int i = 0; i < length; i++)
    {
        if (chars[i] == delimiter)
        {
            token_count++;
        }
    }

    void *array = new(Array, String, token_count);

    int token_index = 0;
    int token_start = 0;
    for (int i = 0; i <= length; i++)
    {
        if (chars[i] == '\0' || chars[i] == delimiter)
        {
            char *token = (char *)malloc(i - token_start + 1);
            strncpy(token, chars + token_start, i - token_start);
            
            set(array, token_index, new(String, token));
            free(token);

            token_index++;
            token_start = i + 1;
        }
    }

    return array;
}

void *substring(void *self, unsigned int start, unsigned int end)
{
    struct String *instance = self;
    char *chars = instance->chars;
    int length = instance->length;

    assert(start >= 0 && start <= length);  
    assert(end >= 0 && end <= length);
    assert(start <= end);

    char *substring_chars = malloc(end - start + 1);
    strncpy(substring_chars, chars + start, end - start);
    substring_chars[end - start] = '\0';

    void *substring = new (String, substring_chars);
    free(substring_chars);

    return substring;
}

char *to_char_array(void *self)
{
    struct String *instance = self;
    char *chars = instance->chars;
    int length = instance->length;

    char *char_array = malloc(length + 1);
    strncpy(char_array, chars, length);
    char_array[length] = '\0';

    return char_array;
}

void *to_lower(void *self)
{
    struct String *instance = self;
    char *chars = instance->chars;
    int length = instance->length;

    char *lowercase_chars = malloc(length + 1);
    for (int i = 0; i < length; i++)
    {
        lowercase_chars[i] = tolower(chars[i]);
    }

    lowercase_chars[length] = '\0';

    void *lowercase_string = new (String, lowercase_chars);
    free(lowercase_chars);

    return lowercase_string;
}

void *to_upper(void *self)
{
    struct String *instance = self;
    char *chars = instance->chars;
    int length = instance->length;

    char *uppercase_chars = malloc(length + 1);
    for (int i = 0; i < length; i++)
    {
        uppercase_chars[i] = toupper(chars[i]);
    }   
    uppercase_chars[length] = '\0';

    void *uppercase_string = new (String, uppercase_chars);
    free(uppercase_chars);

    return uppercase_string;
}

void *trim(void *self)
{
    struct String *instance = self;
    char *chars = instance->chars;
    int length = instance->length;

    int start = 0;
    int end = length - 1;
    while (start < length && isspace(chars[start]))
    {
        start++;
    }

    while (end >= 0 && isspace(chars[end]))
    {
        end--;
    }

    int trimmed_length = end - start + 1;
    char *trimmed_chars = malloc(trimmed_length + 1);
    strncpy(trimmed_chars, chars + start, trimmed_length);
    trimmed_chars[trimmed_length] = '\0';

    void *trimmed_string = new (String, trimmed_chars);
    free(trimmed_chars);

    return trimmed_string;
}

void print(void *self)
{
    struct String *instance = self;
    char *chars = instance->chars;

    printf("%s", chars);
}

void println(void *self)
{
    struct String *instance = self;
    char *chars = instance->chars;

    printf("%s\n", chars);
}

