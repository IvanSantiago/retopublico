Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '../ext-3.2.1/resources/images/default/s.gif';
	
    var SEGPERMISOXGPO = Ext.data.Record.create([
	{	
		name: 'ICVESISTEMA'
		,mapping: 'ICVESEGPERMISOXGPO_PK.ICVESISTEMA'
		,type:'int'
	},
	{	
		name: 'ICVEGRUPO'
		,mapping: 'ICVESEGPERMISOXGPO_PK.ICVEGRUPO'
		,type: 'int'
	},{	
		name: 'ICVEPROGRAMA'
		,mapping: 'ICVESEGPERMISOXGPO_PK.ICVEPROGRAMA'
		,type: 'int'
	},{	
		name: 'LEJECUCION'
			,mapping: 'LEJECUCION'
			,type: 'int'
	},{	
		name: 'LACTUALIZACION'
			,mapping: 'LACTUALIZACION'
			,type: 'int'
	}
		]);    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'SEGPERMISOXGPO_view.action',
            create : 'SEGPERMISOXGPO_create.action',
            update: 'SEGPERMISOXGPO_update.action',
            destroy: 'SEGPERMISOXGPO_delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: "ICVESEGPERMISOXGPO_ID",
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    SEGPERMISOXGPO);
    
 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
        ,toHash: function(rec) {
    	    return {
				ICVESEGPERMISOXGPO_PK: {
					ICVEPAIS : rec.get('ICVEPAIS')
					,ICVEENTIDADFED : rec.get('ICVEENTIDADFED')
				}
				, CNOMBRE : rec.get('CNOMBRE')
				, CABREVIATURA : rec.get('CABREVIATURA')
				, ICVESEGPERMISOXGPO_ID : rec.get('ICVEPAIS')+"-"+rec.get('ICVEENTIDADFED')
    	    };
    	  }
    });
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'ICVESEGPERMISOXGPO_ID',
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
			/*{header: "ICVESEGPERMISOXGPO_ID",
			    width: 170,
			    sortable: true,
			    dataIndex: 'ICVESEGPERMISOXGPO_ID',
			    editor: {
			       xtype: 'textfield',
			       allowBlank: false
			}},*/
			{header: "ICVESISTEMA",
			    width: 170,
			    sortable: true,
			    dataIndex: 'ICVESISTEMA',
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
            {header: "ICVEGRUPO",
             width: 170,
             sortable: true,
             dataIndex: 'ICVEGRUPO',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }},
            {header: "ICVEPROGRAMA",
             width: 160,
             sortable: true,
             dataIndex: 'ICVEPROGRAMA',
             editor: {
                 xtype: 'textfield',
                 allowBlank: false
            }},
            {header: "LEJECUCION",
                width: 160,
                sortable: true,
                dataIndex: 'LEJECUCION',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "LACTUALIZACION",
                width: 160,
                sortable: true,
                dataIndex: 'LACTUALIZACION',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }}
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'SEGPERMISOXGPO',
        height: 400,
        //width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Nuevo registro',
            handler: function(){
                var e = new SEGPERMISOXGPO({
                	ICVEENTIDADFED: '0'
                    , CNOMBRE: 'X X X'
                    , CABREVIATURA: 'Y Y Y'
                    , ICVEPAIS: '0'
                    , ICVESEGPERMISOXGPO_ID:'0-0'                                       	
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