import{j as v,M as h,N as C,c as n,h as t,aa as k,s as q,aA as y,aB as _,aC as S,y as x}from"./index.167deede.js";var $=v({name:"QBreadcrumbsEl",props:{...h,label:String,icon:String,tag:{type:String,default:"span"}},emits:["click"],setup(e,{slots:l}){const{linkTag:u,linkAttrs:c,linkClass:o,navigateOnClick:d}=C(),r=n(()=>({class:"q-breadcrumbs__el q-link flex inline items-center relative-position "+(e.disable!==!0?"q-link--focusable"+o.value:"q-breadcrumbs__el--disable"),...c.value,onClick:d})),i=n(()=>"q-breadcrumbs__el-icon"+(e.label!==void 0?" q-breadcrumbs__el-icon--with-label":""));return()=>{const s=[];return e.icon!==void 0&&s.push(t(k,{class:i.value,name:e.icon})),e.label!==void 0&&s.push(e.label),t(u.value,{...r.value},q(l.default,s))}}});const Q=["",!0];var E=v({name:"QBreadcrumbs",props:{...y,separator:{type:String,default:"/"},separatorColor:String,activeColor:{type:String,default:"primary"},gutter:{type:String,validator:e=>["none","xs","sm","md","lg","xl"].includes(e),default:"sm"}},setup(e,{slots:l}){const u=_(e),c=n(()=>`flex items-center ${u.value}${e.gutter==="none"?"":` q-gutter-${e.gutter}`}`),o=n(()=>e.separatorColor?` text-${e.separatorColor}`:""),d=n(()=>` text-${e.activeColor}`);return()=>{const r=S(x(l.default));if(r.length===0)return;let i=1;const s=[],m=r.filter(a=>a.type!==void 0&&a.type.name==="QBreadcrumbsEl").length,g=l.separator!==void 0?l.separator:()=>e.separator;return r.forEach(a=>{if(a.type!==void 0&&a.type.name==="QBreadcrumbsEl"){const b=i<m,f=a.props!==null&&Q.includes(a.props.disable),p=(b===!0?"":" q-breadcrumbs--last")+(f!==!0&&b===!0?d.value:"");i++,s.push(t("div",{class:`flex items-center${p}`},[a])),b===!0&&s.push(t("div",{class:"q-breadcrumbs__separator"+o.value},g()))}else s.push(a)}),t("div",{class:"q-breadcrumbs"},[t("div",{class:c.value},s)])}}});export{E as Q,$ as a};
