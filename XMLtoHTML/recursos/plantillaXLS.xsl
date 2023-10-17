<?xml version="1.0" encoding='ISO-8859-1'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template match='/'>
   <html><xsl:apply-templates /></html>
 </xsl:template>
 <xsl:template match='Departamentos'>
    <head><title>LISTADO DE DEPARTAMENTOS</title></head>
    <body> 
    <h1>LISTA DE DEPARTAMENTOS</h1>
    <table border='1'>
    <tr><th align="center">Nº de Departamento</th><th align="center">Nombre</th><th align="center">Localidad</th></tr>
      <xsl:apply-templates select='Departamento' />
    </table>
    </body>
 </xsl:template>
 <xsl:template match='Departamento'>
   <tr><xsl:apply-templates /></tr>
 </xsl:template>
 <xsl:template match='id|nombre|localidad'>
   <td align="center"><xsl:apply-templates /></td>
 </xsl:template>
</xsl:stylesheet>