<?xml version="1.0" encoding='ISO-8859-1'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template match='/'>
   <html><xsl:apply-templates /></html>
 </xsl:template>
 <xsl:template match='Platos'>
    <head><title>LISTADO DE PLATOS</title></head>
    <body style="background-color: black; text-align: center;"> 
    <h1 style="color: white;">LISTA DE PLATOS</h1>
    <table border="1" cellspacing="10" style="margin: 0 auto; background-color: white; width: 80%;">
    <!-- <tr><th align="center">Nombre del plato</th><th align="center">Lugar</th><th align="center">Precio</th><th align="center">Puntuacion</th></tr> -->
        <tr>
            <th>Nombre</th>
            <th>Lugar</th>
            <th>Precio</th>
            <th>Puntuación</th>
        </tr>
        
      <xsl:apply-templates select='Plato' />
    </table>
    </body>
 </xsl:template>
 <xsl:template match='Plato'>
   <tr><xsl:apply-templates /></tr>
 </xsl:template>
 <xsl:template match='nombre|lugar|precio|puntuacion'>
   <td align="center"><xsl:apply-templates /></td>
 </xsl:template>
</xsl:stylesheet>