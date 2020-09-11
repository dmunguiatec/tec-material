# IC-3002 Notas de clase #

Para generar los pdfs de todos los archivos

```bash
find ./ -iname "[aci]*.md" -type f  -exec sh -c 'echo ${0} && pandoc "${0}" -o "${0%.md}.pdf"' {} \;
```

