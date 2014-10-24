Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '../ext-3.2.1/resources/images/default/s.gif';

    var IMAGEN = Ext.data.Record.create([
	{	
		name: 'ICVEIMAGEN_ID'
		,mapping: 'ICVEIMAGEN_ID'
		,type:'string'
	},
	{	
		name: 'ICVEIMAGEN'
		,mapping: 'ICVEIMAGEN_PK.ICVEIMAGEN'
		,type: 'int'
	}, {
        name: 'ICVECOMENTARIO',
        type: 'int'
	}, {
        name: 'CTIPOIMAGEN',
        type: 'string'
	}]);    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'IMAGENES_view.action',
            create : 'IMAGENES_create.action',
            update : 'IMAGENES_update.action',
            destroy : 'IMAGENES_delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: "ICVEIMAGEN_ID",
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    IMAGEN);
    
 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
        ,toHash: function(rec) {
    	    return {
    	    	ICVEIMAGEN_PK: {
    	    		ICVEIMAGEN : rec.get('ICVEIMAGEN')
				}
				, ICVECOMENTARIO : rec.get('ICVECOMENTARIO')
				, CTIPOIMAGEN : rec.get('CTIPOIMAGEN')						
				, ICVEIMAGEN_ID : rec.get('ICVEIMAGEN')+"-"
    	    };
    	  }
    });
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'ICVEIMAGEN',
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
			    dataIndex: 'ICVEIMAGEN',
			    editor: {
			       xtype: 'textfield',
			       readOnly: true,
			       allowBlank: false
			    }
			},
            {header: "Cve Comentario",
             width: 170,
             sortable: true,
             dataIndex: 'ICVECOMENTARIO',
             editor: {
            	 xtype: 'numberfield',
                 allowDecimals: false,
                 allowNegative: false, 
                 allowBlank: false,                         
            }},
            {header: "Tipo Imagen",
                width: 170,
                sortable: true,
                dataIndex: 'CTIPOIMAGEN',
                editor: {
                   xtype: 'textfield',
                   allowBlank: false                 
             }}
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'IMAGENES',
        height: 400,
        //width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Nuevo registro',
            handler: function(){
                var e = new IMAGEN({
                	ICVEIMAGEN: '0'
                	, ICVECOMENTARIO: '0'
                	, CTIPOIMAGEN: 'XXX'
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