def gato():
   tablero = [[None,None,None],[None,None,None],[None,None,None]]
   seguir_jugando = True
   jugador = True

   while seguir_jugando:

      disponibles = False
      for fila in tablero:
         for columna in fila:
            if columna == None:
               disponibles = True
               columna = ' '
            print '[' + columna + '] ',
         print ''

      if not disponibles:
         seguir_jugando = False
      else:
         if (jugador):
            marca = 'X'
         else:
            marca = 'O'

         entrada = input('Coloque su ' + marca + ' (fila,col): ')
         tablero[entrada[0]][entrada[1]] = marca

         diagonal = True      
         for i in range(0, 3):
            fila = True
            for j in range(0, 3):
               if i == j:
                  diagonal = diagonal and tablero[i][j] == marca
               fila = fila and tablero[i][j] == marca

            if fila:
               print 'Felicidades ' + marca + ' has ganado!'
               seguir_jugando = False

         if seguir_jugando and diagonal:
            print 'Felicidades ' + marca + ' has ganado!'
            seguir_jugando = False

         if seguir_jugando:
            for j in range(0, 3):
               columna = True
               for i in range(0, 3):
                  columna = columna and tablero[i][j] == marca

               if columna:
                  print 'Felicidades ' + marca + ' has ganado!'
                  seguir_jugando = False

         jugador = not jugador

   print 'Hasta pronto!'






gato()