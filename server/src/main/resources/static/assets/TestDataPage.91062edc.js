import{a as M,Q as b}from"./QToolbar.e08e31dd.js";import{Q as _}from"./QTooltip.d76faa7c.js";import{_ as v,r as p,H as Q,o as k,J as C,K as N,L as i,d as l,M as D,Q as m,aa as x}from"./index.7f3ba645.js";import{Q as h}from"./QTd.3f015520.js";import{Q as q}from"./QTable.2a36f1bc.js";import{Q as y}from"./QPage.d924841a.js";import{g as f}from"./graphql.6d0adb5b.js";import{d as B}from"./date.bfc3e919.js";import"./QSelect.8ec0c3c9.js";import"./use-key-composition.450dd12f.js";import"./use-dark.4c8cd3f0.js";import"./use-timeout.2d83833b.js";import"./QList.d376259e.js";import"./axios.898b20c0.js";const Y=[{name:"name",required:!0,label:"Name",align:"left",field:o=>o.name,format:o=>`${o}`,sortable:!0},{name:"lastModified",label:"Last modified",align:"left",field:o=>B.formatDate(new Date(o.lastModified),"DD-MM-YYYY HH:mm"),sortable:!0},{name:"actions",align:"left",label:"Actions"}],H={setup(){const o=p([]),c=Q(),d=p(!1),t=p([]);function r(){d.value=!0,f(`
          {
            allTestData {
              name
              id
              lastModified
            }
          }
        `,e=>{if(d.value=!1,!!e.data.allTestData){var a=e.data.allTestData.map(s=>(s.lastModified=Number(s.lastModified),s)).sort((s,w)=>w.lastModified-s.lastModified);o.value=a.map(s=>(s.lastModified=new Date(s.lastModified),s))}},e=>{d.value=!1})}function T(){var e=window.prompt("Test Data Name");!e||f(`mutation {
            createTestData(name:"${e}"){
                 name
                id
                lastModified
             }
          }`,a=>{r()},a=>{})}function n(){if(t.value.length!=0){var e=`mutation {
`;t.value.map(a=>{e+=`del${a.id}:deleteTestData(id:${a.id})
`}),e+=`}
`,console.log(e),f(e,a=>{r()},a=>{})}}function u(e){f(`mutation {
            deleteTestData(id:${e})
          }`,a=>{r()},a=>{})}function g(e){c.push(`testData/${e}`)}return k(()=>{r()}),{rows:o,columns:Y,deleteTestData:u,deleteSelectedTestData:n,createTestData:T,loading:d,editTestData:g,selected:t}}},V={class:"q-pa-md q-gutter-sm"};function $(o,c,d,t,r,T){return C(),N(y,null,{default:i(()=>[l(b,{class:"bg-primary text-white"},{default:i(()=>[l(M,null,{default:i(()=>[D("Test Data")]),_:1}),l(m,{flat:"",round:"",dense:"",icon:"delete",onClick:t.deleteSelectedTestData},{default:i(()=>[l(_,null,{default:i(()=>[D("Delete TestData")]),_:1})]),_:1},8,["onClick"]),l(m,{flat:"",round:"",dense:"",icon:"add",onClick:t.createTestData},{default:i(()=>[l(_,null,{default:i(()=>[D("New TestData")]),_:1})]),_:1},8,["onClick"])]),_:1}),x("div",V,[l(q,{columns:t.columns,rows:t.rows,loading:t.loading,"row-key":"id",selection:"multiple",selected:t.selected,"onUpdate:selected":c[0]||(c[0]=n=>t.selected=n)},{"body-cell-actions":i(n=>[l(h,{key:"action",props:n},{default:i(()=>[l(m,{flat:"",size:"sm","no-caps":"",label:"Edit",color:"primary",onClick:u=>t.editTestData(n.row.id)},null,8,["onClick"]),l(m,{flat:"",size:"sm","no-caps":"",label:"Delete",color:"red",onClick:u=>t.deleteTestData(n.row.id)},null,8,["onClick"])]),_:2},1032,["props"])]),_:1},8,["columns","rows","loading","selected"])])]),_:1})}var O=v(H,[["render",$]]);export{O as default};
