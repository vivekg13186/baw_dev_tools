import{_ as x,G as k,H as y,r as u,o as D,I as T,J as Q,K as b,L as m,d as o,Q as f,M as h,ay as C}from"./index.7f3ba645.js";import{a as w,Q as B}from"./QToolbar.e08e31dd.js";import{Q as V}from"./QPage.d924841a.js";import{u as E}from"./use-quasar.c7be712f.js";import{T as M,o as N}from"./index.09194b24.js";import{j as P}from"./index.5fd7bf99.js";import{g as p}from"./graphql.6d0adb5b.js";import"./axios.898b20c0.js";const R={components:{Codemirror:M},setup(){const l=E(),n=[P(),N],d=k(),a=y(),r=u(d.params.name),e=u(),s=u(d.params.id);function c(){a.back()}function v(t){e.value=t.view}function _(){p(`
          {
            getTestData(id: ${s.value}) {
              id
              lastModified
              data
              name
            }
          }
        `,t=>{var i=t.data.getTestData;r.value=i.name,e.value=i.data})}function g(){var t=JSON.stringify(e.value);p(`
         mutation{
  updateTestData(id:${s.value},name:"${r.value}",data:${t}){
    id
  }
}
        `,i=>{l.notify("Saved")},i=>{l.notify("Error while saving")})}return D(()=>{_()}),{name:r,code:e,saveData:g,goBack:c,handleReady:v,extensions:n}}};function S(l,n,d,a,r,e){const s=T("codemirror");return Q(),b(V,null,{default:m(()=>[o(B,{class:"bg-primary text-white"},{default:m(()=>[o(f,{flat:"",icon:"arrow_back",onClick:a.goBack},null,8,["onClick"]),o(w,null,{default:m(()=>[h(C(a.name),1)]),_:1}),o(f,{flat:"",icon:"save",onClick:a.saveData},null,8,["onClick"])]),_:1}),o(s,{modelValue:a.code,"onUpdate:modelValue":n[0]||(n[0]=c=>a.code=c),style:{height:"600px"},autofocus:!0,"indent-with-tab":!0,"tab-size":2,extensions:a.extensions},null,8,["modelValue","extensions"])]),_:1})}var K=x(R,[["render",S]]);export{K as default};
