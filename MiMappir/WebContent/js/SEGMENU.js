Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '../ext-3.2.1/resources/images/default/s.gif';
	
    var SEGMENU = Ext.data.Record.create([
	{	
		name: 'ICVESISTEMA'
		,mapping: 'ICVEMENU_PK.ICVESISTEMA'
		,type:'int'
	},
	{	
		name: 'IORDEN'
		,mapping: 'ICVEMENU_PK.IORDEN'
		,type: 'int'
	},{	
		name: 'CREFERENCIA'
		,mapping: 'CREFERENCIA'
		,type: 'string'
	},{	
		name: 'CDSCMENU'
			,mapping: 'CDSCMENU'
			,type: 'string'
	},{	
		name: 'IOPCPADRE'
			,mapping: 'IOPCPADRE'
			,type: 'int'
	},{	
		name: 'LBLOQUEADO'
			,mapping: 'LBLOQUEADO'
			,type: 'int'
	},{	
		name: 'CNOMPAGINA'
			,mapping: 'CNOMPAGINA'
			,type: 'string'
	},{	
		name: 'CCLASECSS'
			,mapping: 'CCLASECSS'
			,type: 'string'
	},{	
		name: 'CICONCLS'
			,mapping: 'CICONCLS'
			,type: 'string'
	}
		]);    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'SEGMENU_view.action',
            create : 'SEGMENU_create.action',
            update: 'SEGMENU_update.action',
            destroy: 'SEGMENU_delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: "ICVESEGMENU_ID",
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    SEGMENU);
    
 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
        ,toHash: function(rec) {
    	    return {
				ICVESEGMENU_PK: {
					ICVEPAIS : rec.get('ICVEPAIS')
					,ICVEENTIDADFED : rec.get('ICVEENTIDADFED')
				}
				, CNOMBRE : rec.get('CNOMBRE')
				, CABREVIATURA : rec.get('CABREVIATURA')
				, ICVESEGMENU_ID : rec.get('ICVEPAIS')+"-"+rec.get('ICVEENTIDADFED')
    	    };
    	  }
    });
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'ICVESEGMENU_ID',
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
			/*{header: "ICVESEGMENU_ID",
			    width: 170,
			    sortable: true,
			    dataIndex: 'ICVESEGMENU_ID',
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
            {header: "IORDEN",
             width: 170,
             sortable: true,
             dataIndex: 'IORDEN',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }},
            {header: "CREFERENCIA",
             width: 160,
             sortable: true,
             dataIndex: 'CREFERENCIA',
             editor: {
                 xtype: 'textfield',
                 allowBlank: false
            }},
            {header: "CDSCMENU",
                width: 160,
                sortable: true,
                dataIndex: 'CDSCMENU',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "IOPCPADRE",
                width: 160,
                sortable: true,
                dataIndex: 'IOPCPADRE',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "LBLOQUEADO",
                width: 160,
                sortable: true,
                dataIndex: 'LBLOQUEADO',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "CNOMPAGINA",
                width: 160,
                sortable: true,
                dataIndex: 'CNOMPAGINA',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "CCLASECSS",
                width: 160,
                sortable: true,
                dataIndex: 'CCLASECSS',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "CICONCLS",
                width: 160,
                sortable: true,
                dataIndex: 'CICONCLS',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }}
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'SEGMENU',
        height: 400,
        //width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Nuevo registro',
            handler: function(){
                var e = new SEGMENU({
                	ICVEENTIDADFED: '0'
                    , CNOMBRE: 'X X X'
                    , CABREVIATURA: 'Y Y Y'
                    , ICVEPAIS: '0'
                    , ICVESEGMENU_ID:'0-0'                                       	
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