Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '../ext-3.2.1/resources/images/default/s.gif';
	
    var SEGUSUARIO = Ext.data.Record.create([
	{	
		name: 'ICVEUSUARIO'
		,mapping: 'ICVESEGUSUARIO_PK.ICVEUSUARIO'
		,type:'int'
	},
	{	
		name: 'DTREGISTRO'
		,mapping: 'DTREGISTRO'
		,type: 'date'
	},{	
		name: 'CUSUARIO'
		,mapping: 'CUSUARIO'
		,type: 'string'
	},{	
		name: 'CPASSWORD'
			,mapping: 'CPASSWORD'
			,type: 'string'
	},{	
		name: 'CNOMBRE'
			,mapping: 'CNOMBRE'
			,type: 'string'
	},{	
		name: 'CAPPATERNO'
			,mapping: 'CAPPATERNO'
			,type: 'string'
	},{	
		name: 'CAPMATERNO'
			,mapping: 'CAPMATERNO'
			,type: 'string'
	},{	
		name: 'CCALLE'
			,mapping: 'CCALLE'
			,type: 'string'
	},{	
		name: 'CCOLONIA'
			,mapping: 'CCOLONIA'
			,type: 'string'
	},{	
		name: 'ICVEPAIS'
			,mapping: 'ICVEPAIS'
			,type: 'int'
	},{	
		name: 'ICVEENTIDADFED'
			,mapping: 'ICVEENTIDADFED'
			,type: 'int'
	},{	
		name: 'ICVEMUNICIPIO'
			,mapping: 'ICVEMUNICIPIO'
			,type: 'int'
	},{	
		name: 'ICODIGOPOSTAL'
			,mapping: 'ICODIGOPOSTAL'
			,type: 'int'
	},{	
		name: 'CTELEFONO'
			,mapping: 'CTELEFONO'
			,type: 'string'
	},{	
		name: 'ICVEUNIDADORG'
			,mapping: 'ICVEUNIDADORG'
			,type: 'int'
	},{	
		name: 'LBLOQUEADO'
			,mapping: 'LBLOQUEADO'
			,type: 'int'
	},{	
		name: 'CECORREO'
			,mapping: 'CECORREO'
			,type: 'string'
	},{	
		name: 'DTCAMBIOCONTRA'
			,mapping: 'DTCAMBIOCONTRA'
			,type: 'date'
	}
		]);    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'SEGUSUARIO_view.action',
            create : 'SEGUSUARIO_create.action',
            update: 'SEGUSUARIO_update.action',
            destroy: 'SEGUSUARIO_delete.action'
        }
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: "ICVESEGUSUARIO_ID",
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    SEGUSUARIO);
    
 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
        ,toHash: function(rec) {
    	    return {
				ICVESEGUSUARIO_PK: {
					ICVEUSUARIO : rec.get('ICVEUSUARIO')
				}
				, DTREGISTRO : rec.get('DTREGISTRO')
				, CUSUARIO : rec.get('CUSUARIO')
				, CPASSWORD : rec.get('CPASSWORD')
				, CNOMBRE : rec.get('CNOMBRE')
				, CAPPATERNO : rec.get('CAPPATERNO')
				, CAPMATERNO : rec.get('CAPMATERNO')
				, CCALLE : rec.get('CCALLE')
				, CCOLONIA : rec.get('CCOLONIA')
				, ICVEPAIS : rec.get('ICVEPAIS')
				, ICVEENTIDADFED : rec.get('ICVEENTIDADFED')
				, ICVEMUNICIPIO : rec.get('ICVEMUNICIPIO')
				, ICODIGOPOSTAL : rec.get('ICODIGOPOSTAL')
				, CTELEFONO : rec.get('CTELEFONO')
				, ICVEUNIDADORG : rec.get('ICVEUNIDADORG')
				, LBLOQUEADO : rec.get('LBLOQUEADO')
				, CECORREO : rec.get('CECORREO')
				, DTCAMBIOCONTRA : rec.get('DTCAMBIOCONTRA')
				, ICVESEGUSUARIO_ID : rec.get('ICVEUSUARIO')
    	    };
    	  }
    });
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'ICVESEGUSUARIO_ID',
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
			/*{header: "ICVESEGUSUARIO_ID",
			    width: 170,
			    sortable: true,
			    dataIndex: 'ICVESEGUSUARIO_ID',
			    editor: {
			       xtype: 'textfield',
			       allowBlank: false
			}},*/
			{header: "ICVEUSUARIO",
			    width: 170,
			    sortable: true,
			    dataIndex: 'ICVEUSUARIO',
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
            {header: "DTREGISTRO",
             width: 170,
             sortable: true,
             dataIndex: 'DTREGISTRO',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }},
            {header: "CUSUARIO",
             width: 160,
             sortable: true,
             dataIndex: 'CUSUARIO',
             editor: {
                 xtype: 'textfield',
                 allowBlank: false
            }},
            {header: "CPASSWORD",
                width: 160,
                sortable: true,
                dataIndex: 'CPASSWORD',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "CNOMBRE",
                width: 160,
                sortable: true,
                dataIndex: 'CNOMBRE',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "CAPPATERNO",
                width: 160,
                sortable: true,
                dataIndex: 'CAPPATERNO',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "CAPMATERNO",
                width: 160,
                sortable: true,
                dataIndex: 'CAPMATERNO',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "CCALLE",
                width: 160,
                sortable: true,
                dataIndex: 'CCALLE',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "CCOLONIA",
                width: 160,
                sortable: true,
                dataIndex: 'CCOLONIA',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "ICVEPAIS",
                width: 160,
                sortable: true,
                dataIndex: 'ICVEPAIS',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "ICVEENTIDADFED",
                width: 160,
                sortable: true,
                dataIndex: 'ICVEENTIDADFED',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "ICVEMUNICIPIO",
                width: 160,
                sortable: true,
                dataIndex: 'ICVEMUNICIPIO',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "ICODIGOPOSTAL",
                width: 160,
                sortable: true,
                dataIndex: 'ICODIGOPOSTAL',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "CTELEFONO",
                width: 160,
                sortable: true,
                dataIndex: 'CTELEFONO',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "ICVEUNIDADORG",
                width: 160,
                sortable: true,
                dataIndex: 'ICVEUNIDADORG',
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
            {header: "CECORREO",
                width: 160,
                sortable: true,
                dataIndex: 'CECORREO',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }},
            {header: "DTCAMBIOCONTRA",
                width: 160,
                sortable: true,
                dataIndex: 'DTCAMBIOCONTRA',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
            }}
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'SEGUSUARIO',
        height: 400,
        //width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Nuevo registro',
            handler: function(){
                var e = new SEGUSUARIO({
                	ICVEENTIDADFED: '0'
                    , CNOMBRE: 'X X X'
                    , CABREVIATURA: 'Y Y Y'
                    , ICVEPAIS: '0'
                    , ICVESEGUSUARIO_ID:'0-0'                                       	
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