Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '../ext-3.2.1/resources/images/default/s.gif';

    var COMENTARIOHISTO = Ext.data.Record.create([
	{	
		name: 'ICVECOMENTARIO_ID'
		,mapping: 'ICVECOMENTARIO_ID'
		,type:'string'
	},
	{	
		name: 'ICVECOMENTARIO'
		,mapping: 'ICVECOMENTARIO_PK.ICVECOMENTARIO'
		,type: 'int'
	}, {
        name: 'ICVEPARTICIPANTE',
        type: 'int'
	}, {
        name: 'IPARTICIPA',
        type: 'int'
	}, {
        name: 'DTFECHAREVISION',
        type: 'date'
	}, {
        name: 'DTFECHASORTEO',
        type: 'date'
	}, {
        name: 'IPUBLICADO',
        type: 'int'
	}, {
        name: 'ICVEUSUARIOREVISOR',
        type: 'int'
	}, {
        name: 'ICVEPREMIACION',
        type: 'int'
    }]);    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'COMENTARIOSHISTO_view.action',
            create : 'COMENTARIOSHISTO_create.action',
            update : 'COMENTARIOSHISTO_update.action',
            destroy : 'COMENTARIOSHISTO_delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: "ICVECOMENTARIO_ID",
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    COMENTARIOHISTO);
    
 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
        ,toHash: function(rec) {
    	    return {
    	    	ICVECOMENTARIO_PK: { ICVECOMENTARIO : rec.get('ICVECOMENTARIO') }
				, ICVEPARTICIPANTE : rec.get('ICVEPARTICIPANTE')
				, IPARTICIPA : rec.get('IPARTICIPA')				
				, DTFECHAREVISION : rec.get('DTFECHAREVISION')
				, DTFECHASORTEO : rec.get('DTFECHASORTEO')					
				, IPUBLICADO : rec.get('IPUBLICADO')	
				, ICVEUSUARIOREVISOR : rec.get('ICVEUSUARIOREVISOR')
				, ICVEPREMIACION : rec.get('ICVEPREMIACION')				
				, ICVECOMENTARIO_ID : rec.get('ICVECOMENTARIO')+"-"
    	    };
    	  }
    });
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'ICVECOMENTARIO',
        proxy: proxy,
        reader: reader,
        writer: writer,  // <-- plug a DataWriter into the store just as you would a Reader
        autoSave: false // <-- false would delay executing create, update, destroy requests until specifically told to do so with some [save] buton.
    });

    //read the data from simple array
    store.load();
    
    Ext.data.DataProxy.addListener('exception', function(proxy, type, action, options, res) {
    	Ext.Msg.show({
    		title: 'ERROR',
//    		msg: res.message,
    		msg:'Error de configuracion',
    		icon: Ext.MessageBox.ERROR,
    		buttons: Ext.Msg.OK
    	});
    });

    
    var editor = new Ext.ux.grid.RowEditor({
        saveText: 'Actualizar',
        cancelText: 'Cancelar'    
    });    
    
    // create grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
			{header: "Clave",
			    width: 170,
			    sortable: true,
			    dataIndex: 'ICVECOMENTARIO',
			    editor: {
			       xtype: 'textfield',
			       readOnly: true,
			       allowBlank: false
			    }
			},
            {header: "Clave Participante",
             width: 170,
             sortable: true,
             dataIndex: 'ICVEPARTICIPANTE',
             editor: {
                xtype: 'numberfield',
                allowBlank: false
            }},
            {header: "Participa",
                width: 170,
                sortable: true,
                dataIndex: 'IPARTICIPA',
             editor: {
                 xtype: 'numberfield',
                 allowDecimals: false,
                 allowNegative: false, 
                 maxLength:1,
                 maxLengthText:"El campo requiere solo 1 digito",
                 allowBlank: false           
             }},
             {header: "Fecha Revision",
                 width: 170,
                 sortable: true,
                 dataIndex: 'DTFECHAREVISION',
                 editor: {
                     xtype: 'datefield', 
                     format:'Y-m-d',
                     allowBlank: false   
             }},
             {header: "Fecha Sorteo",
                 width: 170,
                 sortable: true,
                 dataIndex: 'DTFECHASORTEO',
                 editor: {
                	 xtype: 'datefield', 
                     format:'Y-m-d',
                     allowBlank: false
             }},
             {header: "Publicado",
                 width: 170,
                 sortable: true,
                 dataIndex: 'IPUBLICADO',
                 editor: {
                     xtype: 'numberfield',
                     allowDecimals: false,
                     allowNegative: false, 
                     maxLength:1,
                     maxLengthText:"El campo requiere solo 1 digito",
                     allowBlank: false         
             }},
             {header: "Cve Usuario Revisor",
                 width: 170,
                 sortable: true,
                 dataIndex: 'ICVEUSUARIOREVISOR',
                 editor: {
                	 xtype: 'numberfield',
                     allowBlank: false  
             }},
             
             {header: "Cve Premiacion",
                 width: 170,
                 sortable: true,
                 dataIndex: 'ICVEPREMIACION',
                 editor: {
                	 xtype: 'numberfield',
                     allowBlank: false  
             }}          
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'COMENTARIOS HISTORICOS',
        height: 400,
        //width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Nuevo registro',
            handler: function(){

                var e = new COMENTARIOHISTO({
                	ICVECOMENTARIO: '0'
                	, ICVEPARTICIPANTE: '0'
                	, IPARTICIPA: '0'
                	, DTFECHAREVISION: new Date()
            		, DTFECHASORTEO: new Date()
                    , IPUBLICADO: '0'  
                    , ICVEUSUARIOREVISOR: '0'
                    , ICVEPREMIACION: '0'
                    , ICVECOMENTARIO_ID: '0-'
                });
                editor.stopEditing();
                store.insert(0, e);
                grid.getView().refresh();
                grid.getSelectionModel().selectRow(0);
                editor.startEditing(0);
            }
        },{
            iconCls: 'icon-delete',
            text: 'Eliminar registro',
            handler: function(){
                editor.stopEditing();
                var s = grid.getSelectionModel().getSelections();
                for(var i = 0, r; r = s[i]; i++){
                    store.remove(r);
                }
            }
        },{
            iconCls: 'icon-save',
            text: 'Guardar Cambios',
            handler: function(){
                store.save();
            }
        }]
    });

    //render to DIV
    grid.render('crud-grid');
    
});