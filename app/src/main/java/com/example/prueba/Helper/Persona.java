package com.example.prueba.Helper;



import java.io.Serializable;



public class Persona implements Serializable
{

    private float id_Persona;
    private float id_Cartera;
    private String cartera = null;
    private String codigo = null;
    private String nombres;
    private String apellidos;
    private String nombreCompleto;
    private String telefono;
    private String nit;
    private String dui;
    private String pasaporte = null;
    private boolean pone_Huella;
    private float id_Departamento_Pais_Domicilio;
    private float id_Municipio_Departamento_Domicilio;
    private String domicilio;
    private String latitud_Domicilio = null;
    private String longitud_Domicilio = null;
    private boolean es_Empleado;
    private float id_Departamento_Pais_Direccion_Trabajo;
    private float id_Municipio_Departamento_Direccion_Trabajo;
    private String direccion_Trabajo = null;
    private String latitud_Direccion_Trabajo = null;
    private String longitud_Direccion_Trabajo = null;
    private String telefono_Trabajo = null;
    private String cargo_Trabajo = null;
    private String nombre_Jefe_Inmediato = null;
    private boolean es_Comerciante;
    private float id_Departamento_Pais_Direccion_Negocio;
    private float id_Municipio_Departamento_Direccion_Negocio;
    private String direccion_Negocio;
    private String latitud_Direccion_Negocio = null;
    private String longitud_Direccion_Negocio = null;
    private String giro_Comercial;
    private float ingresos_Mensuales;
    private float id_Departamento_Pais_Nacimiento;
    private float id_Municipio_Departamento_Nacimiento;
    private String fechaNacimiento;
    private String expedicionDocumentoIdentifcacion = null;
    private String expiracionDocumentoIdentificacion;
    private String isss = null;
    private String nup = null;
    private float estadoFamiliar;
    private float sexo;
    private String ocupacion;
    private String profesion;
    private String nacionalidad;
    private String conocidoPor = null;
    private String usuario = null;
    private String contrasena = null;
    private String correo = null;
    private float personas_Cargo;
    private boolean referencia_Crediticia_Comprobable;
    private float residencia;
    private float edad;
    private boolean tiene_Cargo;
    private boolean familiar_Cargo;
    private boolean asociado_Cargo;
    private String comentario;
    private float ponderacion;
    private float estado_Socio;
    private String persona_Tipo_Persona = null;
    private String persona_Relaciones = null;
    private String persona_Referencias = null;
    private String creditos = null;
    private String ahorros = null;
    private String documentos = null;
    private String evaluaciones = null;
    private String referencia_Bancaria = null;
    private String acciones = null;
    private String evaluaciones_Referencias = null;
    private String catalogos_Socio = null;


    // Getter Methods

    public float getId_Persona() {
        return id_Persona;
    }

    public float getId_Cartera() {
        return id_Cartera;
    }

    public String getCartera() {
        return cartera;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNit() {
        return nit;
    }

    public String getDui() {
        return dui;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public boolean getPone_Huella() {
        return pone_Huella;
    }

    public float getId_Departamento_Pais_Domicilio() {
        return id_Departamento_Pais_Domicilio;
    }

    public float getId_Municipio_Departamento_Domicilio() {
        return id_Municipio_Departamento_Domicilio;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getLatitud_Domicilio() {
        return latitud_Domicilio;
    }

    public String getLongitud_Domicilio() {
        return longitud_Domicilio;
    }

    public boolean getEs_Empleado() {
        return es_Empleado;
    }

    public float getId_Departamento_Pais_Direccion_Trabajo() {
        return id_Departamento_Pais_Direccion_Trabajo;
    }

    public float getId_Municipio_Departamento_Direccion_Trabajo() {
        return id_Municipio_Departamento_Direccion_Trabajo;
    }

    public String getDireccion_Trabajo() {
        return direccion_Trabajo;
    }

    public String getLatitud_Direccion_Trabajo() {
        return latitud_Direccion_Trabajo;
    }

    public String getLongitud_Direccion_Trabajo() {
        return longitud_Direccion_Trabajo;
    }

    public String getTelefono_Trabajo() {
        return telefono_Trabajo;
    }

    public String getCargo_Trabajo() {
        return cargo_Trabajo;
    }

    public String getNombre_Jefe_Inmediato() {
        return nombre_Jefe_Inmediato;
    }

    public boolean getEs_Comerciante() {
        return es_Comerciante;
    }

    public float getId_Departamento_Pais_Direccion_Negocio() {
        return id_Departamento_Pais_Direccion_Negocio;
    }

    public float getId_Municipio_Departamento_Direccion_Negocio() {
        return id_Municipio_Departamento_Direccion_Negocio;
    }

    public String getDireccion_Negocio() {
        return direccion_Negocio;
    }

    public String getLatitud_Direccion_Negocio() {
        return latitud_Direccion_Negocio;
    }

    public String getLongitud_Direccion_Negocio() {
        return longitud_Direccion_Negocio;
    }

    public String getGiro_Comercial() {
        return giro_Comercial;
    }

    public float getIngresos_Mensuales() {
        return ingresos_Mensuales;
    }

    public float getId_Departamento_Pais_Nacimiento() {
        return id_Departamento_Pais_Nacimiento;
    }

    public float getId_Municipio_Departamento_Nacimiento() {
        return id_Municipio_Departamento_Nacimiento;
    }

    public String getFechaNacimiento() {

        return fechaNacimiento;
    }

    public String getExpedicionDocumentoIdentifcacion() {
        return expedicionDocumentoIdentifcacion;
    }

    public String getExpiracionDocumentoIdentificacion() {
        return expiracionDocumentoIdentificacion;
    }

    public String getIsss() {
        return isss;
    }

    public String getNup() {
        return nup;
    }

    public float getEstadoFamiliar() {
        return estadoFamiliar;
    }

    public float getSexo() {
        return sexo;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public String getProfesion() {
        return profesion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getConocidoPor() {
        return conocidoPor;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public float getPersonas_Cargo() {
        return personas_Cargo;
    }

    public boolean getReferencia_Crediticia_Comprobable() {
        return referencia_Crediticia_Comprobable;
    }

    public float getResidencia() {
        return residencia;
    }

    public float getEdad() {
        return edad;
    }

    public boolean getTiene_Cargo() {
        return tiene_Cargo;
    }

    public boolean getFamiliar_Cargo() {
        return familiar_Cargo;
    }

    public boolean getAsociado_Cargo() {
        return asociado_Cargo;
    }

    public String getComentario() {
        return comentario;
    }

    public float getPonderacion() {
        return ponderacion;
    }

    public float getEstado_Socio() {
        return estado_Socio;
    }

    public String getPersona_Tipo_Persona() {
        return persona_Tipo_Persona;
    }

    public String getPersona_Relaciones() {
        return persona_Relaciones;
    }

    public String getPersona_Referencias() {
        return persona_Referencias;
    }

    public String getCreditos() {
        return creditos;
    }

    public String getAhorros() {
        return ahorros;
    }

    public String getDocumentos() {
        return documentos;
    }

    public String getEvaluaciones() {
        return evaluaciones;
    }

    public String getReferencia_Bancaria() {
        return referencia_Bancaria;
    }

    public String getAcciones() {
        return acciones;
    }

    public String getEvaluaciones_Referencias() {
        return evaluaciones_Referencias;
    }

    public String getCatalogos_Socio() {
        return catalogos_Socio;
    }

    // Setter Methods

    public void setId_Persona(float id_Persona) {
        this.id_Persona = id_Persona;
    }

    public void setId_Cartera(float id_Cartera) {
        this.id_Cartera = id_Cartera;
    }

    public void setCartera(String cartera) {
        this.cartera = cartera;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public void setPone_Huella(boolean pone_Huella) {
        this.pone_Huella = pone_Huella;
    }

    public void setId_Departamento_Pais_Domicilio(float id_Departamento_Pais_Domicilio) {
        this.id_Departamento_Pais_Domicilio = id_Departamento_Pais_Domicilio;
    }

    public void setId_Municipio_Departamento_Domicilio(float id_Municipio_Departamento_Domicilio) {
        this.id_Municipio_Departamento_Domicilio = id_Municipio_Departamento_Domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setLatitud_Domicilio(String latitud_Domicilio) {
        this.latitud_Domicilio = latitud_Domicilio;
    }

    public void setLongitud_Domicilio(String longitud_Domicilio) {
        this.longitud_Domicilio = longitud_Domicilio;
    }

    public void setEs_Empleado(boolean es_Empleado) {
        this.es_Empleado = es_Empleado;
    }

    public void setId_Departamento_Pais_Direccion_Trabajo(float id_Departamento_Pais_Direccion_Trabajo) {
        this.id_Departamento_Pais_Direccion_Trabajo = id_Departamento_Pais_Direccion_Trabajo;
    }

    public void setId_Municipio_Departamento_Direccion_Trabajo(float id_Municipio_Departamento_Direccion_Trabajo) {
        this.id_Municipio_Departamento_Direccion_Trabajo = id_Municipio_Departamento_Direccion_Trabajo;
    }

    public void setDireccion_Trabajo(String direccion_Trabajo) {
        this.direccion_Trabajo = direccion_Trabajo;
    }

    public void setLatitud_Direccion_Trabajo(String latitud_Direccion_Trabajo) {
        this.latitud_Direccion_Trabajo = latitud_Direccion_Trabajo;
    }

    public void setLongitud_Direccion_Trabajo(String longitud_Direccion_Trabajo) {
        this.longitud_Direccion_Trabajo = longitud_Direccion_Trabajo;
    }

    public void setTelefono_Trabajo(String telefono_Trabajo) {
        this.telefono_Trabajo = telefono_Trabajo;
    }

    public void setCargo_Trabajo(String cargo_Trabajo) {
        this.cargo_Trabajo = cargo_Trabajo;
    }

    public void setNombre_Jefe_Inmediato(String nombre_Jefe_Inmediato) {
        this.nombre_Jefe_Inmediato = nombre_Jefe_Inmediato;
    }

    public void setEs_Comerciante(boolean es_Comerciante) {
        this.es_Comerciante = es_Comerciante;
    }

    public void setId_Departamento_Pais_Direccion_Negocio(float id_Departamento_Pais_Direccion_Negocio) {
        this.id_Departamento_Pais_Direccion_Negocio = id_Departamento_Pais_Direccion_Negocio;
    }

    public void setId_Municipio_Departamento_Direccion_Negocio(float id_Municipio_Departamento_Direccion_Negocio) {
        this.id_Municipio_Departamento_Direccion_Negocio = id_Municipio_Departamento_Direccion_Negocio;
    }

    public void setDireccion_Negocio(String direccion_Negocio) {
        this.direccion_Negocio = direccion_Negocio;
    }

    public void setLatitud_Direccion_Negocio(String latitud_Direccion_Negocio) {
        this.latitud_Direccion_Negocio = latitud_Direccion_Negocio;
    }

    public void setLongitud_Direccion_Negocio(String longitud_Direccion_Negocio) {
        this.longitud_Direccion_Negocio = longitud_Direccion_Negocio;
    }

    public void setGiro_Comercial(String giro_Comercial) {
        this.giro_Comercial = giro_Comercial;
    }

    public void setIngresos_Mensuales(float ingresos_Mensuales) {
        this.ingresos_Mensuales = ingresos_Mensuales;
    }

    public void setId_Departamento_Pais_Nacimiento(float id_Departamento_Pais_Nacimiento) {
        this.id_Departamento_Pais_Nacimiento = id_Departamento_Pais_Nacimiento;
    }

    public void setId_Municipio_Departamento_Nacimiento(float id_Municipio_Departamento_Nacimiento) {
        this.id_Municipio_Departamento_Nacimiento = id_Municipio_Departamento_Nacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setExpedicionDocumentoIdentifcacion(String expedicionDocumentoIdentifcacion) {
        this.expedicionDocumentoIdentifcacion = expedicionDocumentoIdentifcacion;
    }

    public void setExpiracionDocumentoIdentificacion(String expiracionDocumentoIdentificacion) {
        this.expiracionDocumentoIdentificacion = expiracionDocumentoIdentificacion;
    }

    public void setIsss(String isss) {
        this.isss = isss;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public void setEstadoFamiliar(float estadoFamiliar) {
        this.estadoFamiliar = estadoFamiliar;
    }

    public void setSexo(float sexo) {
        this.sexo = sexo;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setConocidoPor(String conocidoPor) {
        this.conocidoPor = conocidoPor;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPersonas_Cargo(float personas_Cargo) {
        this.personas_Cargo = personas_Cargo;
    }

    public void setReferencia_Crediticia_Comprobable(boolean referencia_Crediticia_Comprobable) {
        this.referencia_Crediticia_Comprobable = referencia_Crediticia_Comprobable;
    }

    public void setResidencia(float residencia) {
        this.residencia = residencia;
    }

    public void setEdad(float edad) {
        this.edad = edad;
    }

    public void setTiene_Cargo(boolean tiene_Cargo) {
        this.tiene_Cargo = tiene_Cargo;
    }

    public void setFamiliar_Cargo(boolean familiar_Cargo) {
        this.familiar_Cargo = familiar_Cargo;
    }

    public void setAsociado_Cargo(boolean asociado_Cargo) {
        this.asociado_Cargo = asociado_Cargo;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setPonderacion(float ponderacion) {
        this.ponderacion = ponderacion;
    }

    public void setEstado_Socio(float estado_Socio) {
        this.estado_Socio = estado_Socio;
    }

    public void setPersona_Tipo_Persona(String persona_Tipo_Persona) {
        this.persona_Tipo_Persona = persona_Tipo_Persona;
    }

    public void setPersona_Relaciones(String persona_Relaciones) {
        this.persona_Relaciones = persona_Relaciones;
    }

    public void setPersona_Referencias(String persona_Referencias) {
        this.persona_Referencias = persona_Referencias;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public void setAhorros(String ahorros) {
        this.ahorros = ahorros;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public void setEvaluaciones(String evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public void setReferencia_Bancaria(String referencia_Bancaria) {
        this.referencia_Bancaria = referencia_Bancaria;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public void setEvaluaciones_Referencias(String evaluaciones_Referencias) {
        this.evaluaciones_Referencias = evaluaciones_Referencias;
    }

    public void setCatalogos_Socio(String catalogos_Socio) {
        this.catalogos_Socio = catalogos_Socio;
    }
}
