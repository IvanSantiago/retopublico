Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '../ext-3.2.1/resources/images/default/s.gif';
	
    var CATEGORIA = Ext.data.Record.create([
	{	
		name: 'ICVECATEGORIA_ID'
		,mapping: 'ICVECATEGORIA_ID'
		,type:'string'
	},
	{	
		name: 'ICVECATEGORIA'
		,mapping: 'ICVECATEGORIA_PK.ICVECATEGORIA'
		,type: 'int'
	}, {
        name: 'CDESCRIPCION',
        type: 'string'
    }]);    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'CATEGORIAS_view.action',
            create : 'CATEGORIAS_create.action',
            update : 'CATEGORIAS_update.action',
            destroy : 'CATEGORIAS_delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: "ICVECATEGORIA_ID",
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    CATEGORIA);
    
 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
        ,toHash: function(rec) {
    	    return {
    	    	ICVECATEGORIA_PK: {
    	    		ICVECATEGORIA : rec.get('ICVECATEGORIA')
				}
				, CDESCRIPCION : rec.get('CDESCRIPCION')
				, ICVECATEGORIA_ID : rec.get('ICVECATEGORIA')+"-"
    	    };
    	  }
    });
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'ICVECATEGORIA',
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
			    dataIndex: 'ICVECATEGORIA',
			    editor: {
			       xtype: 'textfield',
			       readOnly: true,
			       allowBlank: false
			    }
			},
            {header: "Descripcion",
             width: 170,
             sortable: true,
             dataIndex: 'CDESCRIPCION',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }},
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'CATEGORIAS',
        height: 400,
        //width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Nuevo registro',
            handler: function(){
                var e = new CATEGORIA({
                	ICVECATEGORIA: '0'
                    , CDESCRIPCION: 'Descripcion'                   
                    , ICVECATEGORIA_ID:'0-'
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