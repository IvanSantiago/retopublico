Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '../ext-3.2.1/resources/images/default/s.gif';
	
    var GRLENTIDADFED = Ext.data.Record.create([
	{	
		name: 'ICVEGRLENTIDADFED_ID'
		,mapping: 'ICVEGRLENTIDADFED_ID'
		,type:'string'
	},
	{	
		name: 'ICVEPAIS'
		,mapping: 'ICVEGRLENTIDADFED_PK.ICVEPAIS'
		,type: 'int'
	},{	
		name: 'ICVEENTIDADFED'
		,mapping: 'ICVEGRLENTIDADFED_PK.ICVEENTIDADFED'
		,type: 'int'
	}, {
        name: 'CNOMBRE',
        type: 'string'
    }, {
        name: 'CABREVIATURA',
        type: 'string'
    }]);    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'GRLENTIDADFED_view.action',
            create : 'GRLENTIDADFED_create.action',
            update: 'GRLENTIDADFED_update.action',
            destroy: 'GRLENTIDADFED_delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: "ICVEGRLENTIDADFED_ID",
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    GRLENTIDADFED);
    
 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
        ,toHash: function(rec) {
    	    return {
				ICVEGRLENTIDADFED_PK: {
					ICVEPAIS : rec.get('ICVEPAIS')
					,ICVEENTIDADFED : rec.get('ICVEENTIDADFED')
				}
				, CNOMBRE : rec.get('CNOMBRE')
				, CABREVIATURA : rec.get('CABREVIATURA')
				, ICVEGRLENTIDADFED_ID : rec.get('ICVEPAIS')+"-"+rec.get('ICVEENTIDADFED')
    	    };
    	  }
    });
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'ICVEGRLENTIDADFED_ID',
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
			/*{header: "ICVEGRLENTIDADFED_ID",
			    width: 170,
			    sortable: true,
			    dataIndex: 'ICVEGRLENTIDADFED_ID',
			    editor: {
			       xtype: 'textfield',
			       allowBlank: false
			}},*/
			{header: "ICVEPAIS",
			    width: 170,
			    sortable: true,
			    dataIndex: 'ICVEPAIS',
			    editor: {
			       xtype: 'textfield',
			       allowBlank: false
			}},
			/*{header: "ICVEENTIDADFED",
			    width: 170,
			    sortable: true,
			    dataIndex: 'ICVEENTIDADFED',
			    editor: {
			       xtype: 'textfield',
			       allowBlank: false
		    }},*/
            {header: "CNOMBRE",
             width: 170,
             sortable: true,
             dataIndex: 'CNOMBRE',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }},
            {header: "CABREVIATURA",
             width: 160,
             sortable: true,
             dataIndex: 'CABREVIATURA',
             editor: {
                 xtype: 'textfield',
                 allowBlank: false
            }}
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'GRLENTIDADFED',
        height: 400,
        //width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Nuevo registro',
            handler: function(){
                var e = new GRLENTIDADFED({
                	ICVEENTIDADFED: '0'
                    , CNOMBRE: 'X X X'
                    , CABREVIATURA: 'Y Y Y'
                    , ICVEPAIS: '0'
                    , ICVEGRLENTIDADFED_ID:'0-0'                                       	
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