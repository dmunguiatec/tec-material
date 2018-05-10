Object subclass: #Person 
    instanceVariableNames: 'firstName lastName' 
    category: 'OnSmalltalk'

person := Person new.

person := (Person new)
                  firstName: 'Ramon'; 
                  lastName: 'Leon'; 
                  yourself.


