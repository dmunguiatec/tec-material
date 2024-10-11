#include <stdio.h>

#include "lang.h"
#include "Object.h"
#include "String.h"
#include "Array.h"

int main(int argc, char *argv[])
{
    const char *spec_chars =
        "# Incrementa el número binario en 1\n"
        "# Estado inicial de la cinta\n"
        "entrada: 1011\n"
        "# Estado inicial del automata\n"
        "inicio: derecha\n";

    struct spec {
        void *entrada;
        void *inicio;
    };

    struct spec spec;

    void *comment_prefix = new(String, "#");
    void *input_prefix = new(String, "entrada:");
    void *initial_state_prefix = new(String, "inicio:");

    void *spec_string = new(String, spec_chars);

    void *lines = split(spec_string, '\n');
    for (int i = 0; i < length(lines); i++) {
        void *line = get(lines, i);
        if (is_empty(line) || !starts_with(line, comment_prefix)) {
            if (starts_with(line, input_prefix)) {
                void *tokens = split(line, ':');
                void *trimmed = trim(get(tokens, 1));

                spec.entrada = trimmed;

                finalize(tokens);

            } else if (starts_with(line, initial_state_prefix)) {
                void *tokens = split(line, ':');
                void *trimmed = trim(get(tokens, 1));

                spec.inicio = trimmed;
                
                finalize(tokens);
            }
        }
    }
    finalize(lines);

    printf("{\n\tentrada: %s,\n\tinicio: %s\n}\n", 
        to_char_array(spec.entrada), to_char_array(spec.inicio));

    finalize(spec.entrada);
    finalize(spec.inicio);

    finalize(spec_string);
    finalize(comment_prefix);
    finalize(input_prefix);
    finalize(initial_state_prefix);

    return 0;
}
