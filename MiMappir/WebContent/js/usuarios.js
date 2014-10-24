Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '../ext-3.2.1/resources/images/default/s.gif';
	
    var USUARIO = Ext.data.Record.create([
	{	
		name: 'ICVEUSUARIO_ID'
		,mapping: 'ICVEUSUARIO_ID'
		,type:'string'
	},
	{	
		name: 'ICVEUSUARIO'
		,mapping: 'ICVEUSUARIO_PK.ICVEUSUARIO'
		,type: 'int'
	}, {
        name: 'CNOMBRE',
        type: 'string'
    }, {
        name: 'DTFECHAREGISTRO',
        type: 'date'
    }]);    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'USUARIOS_view.action',
            create : 'USUARIOS_create.action',
            update : 'USUARIOS_update.action',
            destroy : 'USUARIOS_delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: "ICVEUSUARIO_ID",
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    USUARIO);
    
 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
        ,toHash: function(rec) {
    	    return {
    	    	ICVEUSUARIO_PK: {
    	    		ICVEUSUARIO : rec.get('ICVEUSUARIO')
				}
				, CNOMBRE : rec.get('CNOMBRE')
				, DTFECHAREGISTRO : rec.get('DTFECHAREGISTRO')
				, ICVEUSUARIO_ID : rec.get('ICVEUSUARIO')+"-"
    	    };
    	  }
    });
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'ICVEUSUARIO',
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
    		msg: res.message,
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
			    dataIndex: 'ICVEUSUARIO',
			    editor: {
			       xtype: 'textfield',
			       readOnly: true,
			       allowBlank: false
			    }
			},
            {header: "Nombre Usuario",
             width: 170,
             sortable: true,
             dataIndex: 'CNOMBRE',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }},
            {header: "Fecha de Registro",
             width: 160,
             sortable: true,
             dataIndex: 'DTFECHAREGISTRO', 
             editor: {
                 xtype: 'datefield',                
                 format:'Y-m-d',
                 allowBlank: false,
             }
            }
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'USUARIOS',
        height: 400,
        //width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Nuevo registro',
            handler: function(){
                var e = new USUARIO({
                	ICVEUSUARIO: '0'
                    , CNOMBRE: 'Nombre'                   
                    , DTFECHAREGISTRO: new Date()
                    , ICVEUSUARIO_ID:'0-'
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