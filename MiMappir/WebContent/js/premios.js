Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '../ext-3.2.1/resources/images/default/s.gif';
	
    var PREMIO = Ext.data.Record.create([
	{	
		name: 'ICVEPREMIO_ID'
		,mapping: 'ICVEPREMIO_ID'
		,type:'string'
	},
	{	
		name: 'ICVEPREMIO'
		,mapping: 'ICVEPREMIO_PK.ICVEPREMIO'
		,type: 'int'
	}, {
        name: 'CPREMIO',
        type: 'string'
    }]);    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'PREMIOS_view.action',
            create : 'PREMIOS_create.action',
            update : 'PREMIOS_update.action',
            destroy : 'PREMIOS_delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: "ICVEPREMIO_ID",
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    PREMIO);
    
 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
        ,toHash: function(rec) {
    	    return {
    	    	ICVEPREMIO_PK: {
    	    		ICVEPREMIO : rec.get('ICVEPREMIO')
				}
				, CPREMIO : rec.get('CPREMIO')
				, ICVEPREMIO_ID : rec.get('ICVEPREMIO')+"-"
    	    };
    	  }
    });
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'ICVEPREMIO',
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
			{header: "Id",
			    width: 170,
			    sortable: true,
			    dataIndex: 'ICVEPREMIO',
			    editor: {
			       xtype: 'textfield',
			       readOnly: true,
			       allowBlank: false
			    }
			},
            {header: "Premio",
             width: 170,
             sortable: true,
             dataIndex: 'CPREMIO',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }},
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'PREMIOS',
        height: 400,
        //width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Nuevo registro',
            handler: function(){
                var e = new PREMIO({
                	ICVEPREMIO: '0'
                    , CPREMIO: 'Premio'                   
                    , ICVEPREMIO_ID:'0-'
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