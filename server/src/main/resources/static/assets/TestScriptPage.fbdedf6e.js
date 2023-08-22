import{a as b,Q as v}from"./QToolbar.e08e31dd.js";import{Q as _}from"./QTooltip.d76faa7c.js";import{_ as M,r as u,H as Q,o as k,J as C,K as D,L as i,d as l,M as T,Q as c,aa as N}from"./index.7f3ba645.js";import{Q as q}from"./QTd.3f015520.js";import{Q as x}from"./QTable.2a36f1bc.js";import{Q as h}from"./QPage.d924841a.js";import{d as y}from"./date.bfc3e919.js";import{g as m}from"./graphql.6d0adb5b.js";import"./QSelect.8ec0c3c9.js";import"./use-key-composition.450dd12f.js";import"./use-dark.4c8cd3f0.js";import"./use-timeout.2d83833b.js";import"./QList.d376259e.js";import"./axios.898b20c0.js";const B=[{name:"testscript",label:"Name",align:"left",field:r=>r.name,sortable:!0},{name:"lastModified",label:"Last modified",align:"left",field:r=>y.formatDate(new Date(r.lastModified),"DD-MM-YYYY HH:mm"),sortable:!0},{name:"actions",align:"left",label:"Actions"}],Y={setup(){const r=u([]),n=Q(),f=u([]),e=u(!1);function d(){e.value=!0,m(`
          {
            allTestScript {
              name
              id
              lastModified
            }
          }
        `,t=>{e.value=!1;var a=t.data.allTestScript.map(o=>(o.lastModified=Number(o.lastModified),o)).sort((o,w)=>w.lastModified-o.lastModified);f.value=a.map(o=>(o.lastModified=new Date(o.lastModified),o))},t=>{e.value=!1})}function S(){var t=window.prompt("Test Case Name");!t||m(`mutation {
            createTestScript(name:"${t}"){
                id
            }
          }`,a=>{d()},a=>{})}function s(){if(r.value.length!=0){var t=`mutation {
`;r.value.map(a=>{t+=`del${a.id}:deleteTestScript(id:"${a.id}")
`}),t+=`}
`,console.log(t),m(t,a=>{d()},a=>{})}}function p(t){m(`mutation {
            deleteTestScript(id:"${t}")
          }`,a=>{d()},a=>{})}function g(t){n.push(`testScript/${t}`)}return k(()=>{d()}),{rows:f,columns:B,deleteTestScript:p,selected:r,createTestScript:S,editTestScript:g,deleteSelectedTestScript:s,loading:e}}},H={class:"q-pa-md q-gutter-sm"};function V(r,n,f,e,d,S){return C(),D(h,null,{default:i(()=>[l(v,{class:"bg-primary text-white"},{default:i(()=>[l(b,null,{default:i(()=>[T("Test Scripts")]),_:1}),l(c,{flat:"",round:"",dense:"",icon:"delete",onClick:e.deleteSelectedTestScript},{default:i(()=>[l(_,null,{default:i(()=>[T(" Delete TestScripts ")]),_:1})]),_:1},8,["onClick"]),l(c,{flat:"",round:"",dense:"",icon:"add",onClick:e.createTestScript},{default:i(()=>[l(_,null,{default:i(()=>[T(" New TestScript ")]),_:1})]),_:1},8,["onClick"])]),_:1}),N("div",H,[l(x,{rows:e.rows,columns:e.columns,"row-key":"id",selection:"multiple",selected:e.selected,"onUpdate:selected":n[0]||(n[0]=s=>e.selected=s),loading:e.loading},{"body-cell-actions":i(s=>[l(q,{props:s},{default:i(()=>[l(c,{square:"",flat:"",size:"sm","no-caps":"",label:"Edit",color:"primary",onClick:p=>e.editTestScript(s.row.id)},null,8,["onClick"]),l(c,{square:"",flat:"",size:"sm","no-caps":"",label:"Delete",color:"red",onClick:p=>e.deleteTestScript(s.row.id)},null,8,["onClick"])]),_:2},1032,["props"])]),_:1},8,["rows","columns","selected","loading"])])]),_:1})}var O=M(Y,[["render",V]]);export{O as default};
